import { Routes } from '@angular/router'
import { QuizzesListingComponent } from './pages/quizzes/quizzes-listing/quizzes-listing.component'
import { SessionComponent } from './pages/sessions/session/session.component'

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
    path: 'sessions/:id',
    component: SessionComponent,
  },
]
