import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuizFeedback } from 'src/app/models/quizFeedbackModel/quiz-feedback';

@Injectable({
  providedIn: 'root',
})
export class QuizFeedbackService {
  private apiUrl = 'http://localhost:9292/quizFeedbacks';

  constructor(private http: HttpClient) {}

  saveFeedback(
    userId: number,
    quizId: number,
    feedback: QuizFeedback
  ): Observable<QuizFeedback> {
    return this.http.post<QuizFeedback>(
      `${this.apiUrl}/save/${userId}/quiz/${quizId}`,
      feedback
    );
  }
}
