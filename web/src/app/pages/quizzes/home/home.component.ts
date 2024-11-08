import { Component } from '@angular/core'
import { QuizList, QuizService } from '../../../services/quiz.service'
import { Button } from 'primeng/button'
import { AvatarModule } from 'primeng/avatar'
import { QuizCardComponent } from '../../../components/quiz-card/quiz-card.component'
import { RouterLink } from '@angular/router'

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [Button, AvatarModule, QuizCardComponent, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  quizzes: QuizList[] = []

  constructor(private apiService: QuizService) {
    this.loadQuizzes()
  }

  private loadQuizzes() {
    this.apiService.listAll().subscribe(quizzes => {
      this.quizzes = quizzes
    })
  }
}
