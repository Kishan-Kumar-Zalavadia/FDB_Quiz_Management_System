import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Question } from 'src/app/models/questionModel/question';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  constructor(private http: HttpClient) {}

  private baseUrl = 'http://localhost:9292';

  getAllQuestionsByQuizId(quizId: number): Observable<Question[]> {
    return this.http.get<Question[]>(
      `${this.baseUrl}/quizzes/${quizId}/questions`
    );
  }

  saveQuestion(question: Question): Observable<Question> {
    return this.http.post<Question>(`${this.baseUrl}/questions/save`, question);
  }

  assignQuestionToQuiz(questionId: number, quizId: number): Observable<string> {
    return this.http.put<string>(
      `${this.baseUrl}/questions/${questionId}/assignToQuiz/${quizId}`,
      null
    );
  }
}
