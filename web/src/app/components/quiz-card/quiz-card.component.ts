import { Component, Input } from '@angular/core'
import { QuizList } from '../../services/quiz.service'
import { AvatarModule } from 'primeng/avatar'
import { Button } from 'primeng/button'
import { Router, RouterLink } from '@angular/router'
import { DatePipe } from '@angular/common'
import { PrimeIcons } from 'primeng/api'
import { TooltipModule } from 'primeng/tooltip'
import {
  LaunchSessionRequest,
  SessionsService,
} from '../../services/sessions.service'
import { UserService } from '../../services/user.service'

@Component({
  selector: 'app-quiz-card',
  standalone: true,
  imports: [AvatarModule, Button, RouterLink, DatePipe, TooltipModule],
  templateUrl: './quiz-card.component.html',
  styleUrl: './quiz-card.component.css',
})
export class QuizCardComponent {
  @Input({ required: true })
  quiz!: QuizList

  userIcon = PrimeIcons.USER

  constructor(
    private userService: UserService,
    private sessionsService: SessionsService,
    private router: Router,
  ) {}

  launchSession(quiz: QuizList) {
    const data: LaunchSessionRequest = {
      quizId: quiz.id,
      authorId: this.userService.getUserId(),
    }

    this.sessionsService.launchSession(data).subscribe(session => {
      this.router.navigate([`/sessions/${session.id}`])
    })
  }
}
