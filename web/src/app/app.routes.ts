import { Routes } from '@angular/router'
import { QuizzesListingComponent } from './pages/quizzes/quizzes-listing/quizzes-listing.component'
import { NewQuizComponent } from './pages/quizzes/new-quiz/new-quiz.component'

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'quizzes',
    pathMatch: 'full',
  },
  {
    path: 'quizzes',
    component: QuizzesListingComponent,
    title: 'Quizzes',
  },
  {
    path: 'novo',
    component: NewQuizComponent,
  },
]
