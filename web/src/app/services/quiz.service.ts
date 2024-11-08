import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

export interface QuizList {
  id: string
  title: string
  description: string
  createdDate: Date
}

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  private readonly endpoint = '/api/quizzes'

  constructor(private http: HttpClient) {}

  listAll(): Observable<QuizList[]> {
    return this.http.get<QuizList[]>(this.endpoint)
  }
}
