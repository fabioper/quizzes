import { Component, Input } from '@angular/core'
import { QuizList } from '../../services/quiz.service'
import { AvatarModule } from 'primeng/avatar'
import { Button } from 'primeng/button'
import { RouterLink } from '@angular/router'
import { DatePipe } from '@angular/common'
import { PrimeIcons } from 'primeng/api'
import { TooltipModule } from 'primeng/tooltip'

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
}
