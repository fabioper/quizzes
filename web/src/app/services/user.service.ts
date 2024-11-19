import { Injectable } from '@angular/core'
import { v4 as uuidv4 } from 'uuid'

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly USER_ID_KEY = '_userId'

  generateUserId() {
    const currentUserId = localStorage.getItem(this.USER_ID_KEY)
    if (currentUserId) {
      return currentUserId
    }

    const userId = uuidv4()

    localStorage.setItem(this.USER_ID_KEY, userId)
    return userId
  }

  getUserId() {
    return localStorage.getItem(this.USER_ID_KEY) || this.generateUserId()
  }
}
