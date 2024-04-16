import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAnnouncementComponent } from './add-announcement.component';

describe('AddAnnouncementComponent', () => {
  let component: AddAnnouncementComponent;
  let fixture: ComponentFixture<AddAnnouncementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddAnnouncementComponent]
    });
    fixture = TestBed.createComponent(AddAnnouncementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
