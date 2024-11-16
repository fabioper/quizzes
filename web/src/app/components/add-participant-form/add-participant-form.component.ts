import { Component, EventEmitter, Input, Output } from '@angular/core'
import { Button } from 'primeng/button'
import { InputTextModule } from 'primeng/inputtext'
import { FormsModule } from '@angular/forms'
import {
  LiveSessionParticipant,
  SessionsService,
} from '../../services/sessions.service'
import { UserService } from '../../user.service'
import { tap } from 'rxjs'

@Component({
  selector: 'app-add-participant-form',
  standalone: true,
  imports: [Button, InputTextModule, FormsModule],
  templateUrl: './add-participant-form.component.html',
  styleUrl: './add-participant-form.component.css',
})
export class AddParticipantFormComponent {
  @Input({ required: true })
  sessionId!: string

  @Output()
  participantAdded = new EventEmitter<LiveSessionParticipant>()

  nickname = ''

  constructor(
    private sessionsService: SessionsService,
    private userService: UserService,
  ) {}

  onSubmit() {
    const newParticipant: LiveSessionParticipant = {
      id: this.userService.getUserId(),
      nickname: this.nickname,
    }

    this.sessionsService
      .addParticipant(this.sessionId, newParticipant)
      .pipe(tap(() => this.participantAdded.emit(newParticipant)))
      .subscribe()
  }
}
