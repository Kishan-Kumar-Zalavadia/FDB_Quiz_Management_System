import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quiz } from 'src/app/models/quizModel/quiz';

@Injectable({
  providedIn: 'root',
})
export class QuizService {

  quiz = new Quiz();

  setQuiz(quiz: Quiz) {
    this.quiz = quiz;
  }

  getQuiz(){
    return this.quiz;
  }

  getQuizById(quizId: number) {
    throw new Error('Method not implemented.');
  }
  private apiUrl = 'http://localhost:9292/quizzes'; // Replace 'your-api-url' with your actual API URL

  constructor(private http: HttpClient) {}

  getAllQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.apiUrl);
  }
}
