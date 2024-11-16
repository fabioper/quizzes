import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { StompService } from './stomp.service'
import { of, switchMap } from 'rxjs'
import { IMessage } from '@stomp/rx-stomp'

export interface LaunchSessionRequest {
  quizId: string
  authorId: string
}

export interface LiveSessionParticipant {
  id: string
  nickname: string
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

@Injectable({
  providedIn: 'root',
})
export class SessionsService {
  private readonly endpoint = '/api/sessions'

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

  onSessionUpdate(sessionId: string) {
    return this.stompService
      .watch(`/topic/sessions/${sessionId}/updated`)
      .pipe(
        switchMap(message =>
          of(SessionsService.parseMessage<LiveSession>(message)),
        ),
      )
  }

  private static parseMessage<T>(message: IMessage) {
    return JSON.parse(message.body) as T
  }

  addParticipant(sessionId: string, participant: AddParticipantRequest) {
    this.stompService.publish({
      destination: `/app/sessions/${sessionId}/add-participant`,
      body: JSON.stringify(participant),
    })
  }
}
