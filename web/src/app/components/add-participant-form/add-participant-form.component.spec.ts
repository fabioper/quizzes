import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddParticipantFormComponent } from './add-participant-form.component';

describe('AddParticipantFormComponent', () => {
  let component: AddParticipantFormComponent;
  let fixture: ComponentFixture<AddParticipantFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddParticipantFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddParticipantFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
