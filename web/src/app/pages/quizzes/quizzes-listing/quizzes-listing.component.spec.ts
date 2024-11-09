import { ComponentFixture, TestBed } from '@angular/core/testing'

import { QuizzesListingComponent } from './quizzes-listing.component'

describe('HomeComponent', () => {
  let fixture: ComponentFixture<QuizzesListingComponent>

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QuizzesListingComponent],
    }).compileComponents()

    fixture = TestBed.createComponent(QuizzesListingComponent)
    fixture.detectChanges()
  })
})
