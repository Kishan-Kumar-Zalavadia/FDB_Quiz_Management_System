import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuizAttempt } from 'src/app/models/attemptModel/attempt';
import { Quiz } from 'src/app/models/quizModel/quiz';

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  quiz = new Quiz();

  setQuiz(quiz: Quiz) {
    this.quiz = quiz;
  }

  getQuiz() {
    return this.quiz;
  }

  getQuizById(quizId: number) {
    throw new Error('Method not implemented.');
  }
  private apiUrl = 'http://localhost:9292'; // Replace 'your-api-url' with your actual API URL

  constructor(private http: HttpClient) {}

  getAllQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.apiUrl}/quizzes`);
  }

  calculateScore(selectedOptionIds: number[]): Observable<number> {
    return this.http.post<number>(
      `${this.apiUrl}/options/score`,
      selectedOptionIds
    );
  }

  // * Quiz Attempt
  saveQuizAttempt(
    userId: number,
    quizId: number,
    quizAttempt: QuizAttempt,
    optionIds: number[]
  ): Observable<QuizAttempt> {
    return this.http.post<QuizAttempt>(
      `${this.apiUrl}/quiz-attempts/save/user/${userId}/quiz/${quizId}`,
      { quizAttempt, optionIds }
    );
  }

  // * Quiz Attempt without options
//   saveQuizAttempt(
//     userId: number,
//     quizId: number,
//     quizAttempt: QuizAttempt,
//   ): Observable<QuizAttempt> {
//     return this.http.post<QuizAttempt>(
//       `${this.apiUrl}/quiz-attempts/save/user/${userId}/quiz/${quizId}`,
//       { quizAttempt }
//     );
//   }
}
