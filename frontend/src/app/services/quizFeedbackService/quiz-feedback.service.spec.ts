import { TestBed } from '@angular/core/testing';

import { QuizFeedbackService } from './quiz-feedback.service';

describe('QuizFeedbackService', () => {
  let service: QuizFeedbackService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuizFeedbackService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
