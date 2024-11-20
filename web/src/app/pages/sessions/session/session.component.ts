import { Component } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import {
  LiveSession,
  SessionsService,
} from '../../../services/sessions.service'
import { catchError, EMPTY, switchMap, tap } from 'rxjs'
import { Button } from 'primeng/button'
import { DialogModule } from 'primeng/dialog'
import { AddParticipantFormComponent } from '../../../components/add-participant-form/add-participant-form.component'
import { UserService } from '../../../services/user.service'
import { Message } from 'primeng/api'
import { MessagesModule } from 'primeng/messages'
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http'

@Component({
  selector: 'app-session',
  standalone: true,
  imports: [Button, DialogModule, AddParticipantFormComponent, MessagesModule],
  templateUrl: './session.component.html',
  styleUrl: './session.component.css',
})
export class SessionComponent {
  liveSession?: LiveSession
  showNewParticipantDialog = false
  userIsAdmin = false
  messages: Message[] = []

  constructor(
    private activatedRoute: ActivatedRoute,
    private sessionsService: SessionsService,
    private userService: UserService,
  ) {
    this.loadSession()
      .pipe(
        tap(session => this.checkIfUserIsNewParticipant(session)),
        tap(session => this.setupListeners(session)),
      )
      .subscribe()
  }

  closeNewParticipantDialog() {
    this.showNewParticipantDialog = false
  }

  startQuiz() {
    console.log('start quiz')
  }

  private checkIfUserIsNewParticipant(session: LiveSession) {
    const userId = this.userService.getUserId()
    this.userIsAdmin = userId === session.authorId
    this.showNewParticipantDialog = this.isNewParticipant(session, userId)
  }

  private loadSession() {
    return this.activatedRoute.paramMap.pipe(
      switchMap(params => {
        const sessionId = params.get('id')
        return this.sessionsService.findById(sessionId!)
      }),
      tap(session => (this.liveSession = session)),
      catchError(err => {
        console.error(err)

        if (this.isNotFoundError(err)) {
          this.messages = [
            { detail: 'Sessão não encontrada', severity: 'error' },
          ]
        }

        return EMPTY
      }),
    )
  }

  private isNotFoundError(err: unknown) {
    return (
      err instanceof HttpErrorResponse && err.status === HttpStatusCode.NotFound
    )
  }

  private isNewParticipant(session: LiveSession, userId: string) {
    return (
      !this.userIsAdmin &&
      !session.participants.some(participant => participant.id === userId)
    )
  }

  private setupListeners(session: LiveSession) {
    this.sessionsService
      .onSessionUpdate(session.id)
      .pipe(tap(session => (this.liveSession = session)))
      .subscribe()
  }
}
