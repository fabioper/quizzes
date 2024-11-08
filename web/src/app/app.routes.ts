import { Routes } from '@angular/router'
import { HomeComponent } from './pages/quizzes/home/home.component'
import { NewQuizComponent } from './pages/quizzes/new-quiz/new-quiz.component'

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'quizzes',
    pathMatch: 'full',
  },
  {
    path: 'quizzes',
    component: HomeComponent,
    title: 'Quizzes',
  },
  {
    path: 'novo',
    component: NewQuizComponent,
  },
]
