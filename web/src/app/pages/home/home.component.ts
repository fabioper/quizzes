import { Component, inject } from '@angular/core'
import { Button } from 'primeng/button'
import { ApiService } from '../../services/api.service'
import { toSignal } from '@angular/core/rxjs-interop'

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [Button],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  private apiService = inject(ApiService)

  message = toSignal(this.apiService.getData())
}
