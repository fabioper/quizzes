import { Injectable } from '@angular/core'
import { RxStomp, RxStompConfig } from '@stomp/rx-stomp'

@Injectable({
  providedIn: 'root',
})
export class StompService extends RxStomp {
  private config: RxStompConfig = {
    brokerURL: 'ws://localhost:8080/sessions',
  }

  constructor() {
    super()
    this.configure(this.config)
    this.activate()
  }
}
