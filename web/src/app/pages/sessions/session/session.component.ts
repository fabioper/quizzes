import { Component } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import {
  LiveSession,
  SessionsService,
} from '../../../services/sessions.service'
import { switchMap, tap } from 'rxjs'
import { Button } from 'primeng/button'
import { DialogModule } from 'primeng/dialog'
import { AddParticipantFormComponent } from '../../../components/add-participant-form/add-participant-form.component'
import { UserService } from '../../../user.service'

@Component({
  selector: 'app-session',
  standalone: true,
  imports: [Button, DialogModule, AddParticipantFormComponent],
  templateUrl: './session.component.html',
  styleUrl: './session.component.css',
})
export class SessionComponent {
  liveSession?: LiveSession
  showNewParticipantDialog = false
  userIsAdmin = false

  constructor(
    private activatedRoute: ActivatedRoute,
    private sessionsService: SessionsService,
    private userService: UserService,
  ) {
    this.loadSession()
  }

  private loadSession() {
    return this.activatedRoute.paramMap
      .pipe(
        switchMap(params => {
          const sessionId = params.get('id')
          return this.sessionsService.findById(sessionId!)
        }),
        tap(session => {
          this.liveSession = session
        }),
        tap(session => {
          const userId = this.userService.getUserId()
          this.userIsAdmin = userId === session.authorId

          if (this.isNewParticipant(session)) {
            this.showNewParticipantDialog = true
          }
        }),
        tap(session => this.setupSessionListeners(session)),
      )
      .subscribe()
  }

  private isNewParticipant(session: LiveSession) {
    const userId = this.userService.getUserId()
    return (
      !this.userIsAdmin &&
      !session.participants.some(participant => participant.id === userId)
    )
  }

  private setupSessionListeners(session: LiveSession) {
    this.sessionsService
      .onSessionUpdate(session.id)
      .pipe(tap(session => (this.liveSession = session)))
      .subscribe()
  }

  closeNewParticipantDialog() {
    this.showNewParticipantDialog = false
  }

  initQuiz() {
    console.log('init quiz')
  }
}
