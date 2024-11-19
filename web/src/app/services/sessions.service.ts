import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { StompService } from './stomp.service'
import { Observable, of, switchMap } from 'rxjs'
import { IMessage } from '@stomp/rx-stomp'

export interface LaunchSessionRequest {
  quizId: string
  authorId: string
}

export interface LiveSessionParticipant {
  id: string
  nickname: string
  score: number
}

type LiveSessionStatus = 'preparing' | 'live' | 'finished'

export interface LiveSession {
  id: string
  authorId: string
  status: LiveSessionStatus
  participants: LiveSessionParticipant[]
}

export interface AddParticipantRequest {
  id: string
  nickname: string
}

function parseMessage<T>(source$: Observable<IMessage>) {
  return source$.pipe(
    switchMap(message => {
      return of(JSON.parse(message.body) as T)
    }),
  )
}

@Injectable({
  providedIn: 'root',
})
export class SessionsService {
  private readonly endpoint = '/api/sessions'
  private readonly wsEndpoint = '/app/sessions'
  private readonly topicEndpoint = '/topic/sessions'

  constructor(
    private http: HttpClient,
    private stompService: StompService,
  ) {}

  launchSession(data: LaunchSessionRequest) {
    return this.http.post<LiveSession>(this.endpoint, data)
  }

  findById(sessionId: string) {
    return this.http.get<LiveSession>(`${this.endpoint}/${sessionId}`)
  }

  addParticipant(sessionId: string, participant: AddParticipantRequest) {
    return this.http.post(
      this.endpoint + '/' + sessionId + '/participants',
      participant,
    )
  }

  onSessionUpdate(sessionId: string) {
    return this.stompService
      .watch(`${this.topicEndpoint}/${sessionId}/updated`)
      .pipe(parseMessage<LiveSession>)
  }
}
