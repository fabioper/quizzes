import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { environment } from '../../environments/environment'

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = environment.apiUrl

  constructor(private http: HttpClient) {}

  getData(): Observable<{ message: string }> {
    return this.http.get<{ message: string }>(this.baseUrl + '/api')
  }
}
