import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuizAttempt } from 'src/app/models/attemptModel/attempt';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { Option } from 'src/app/models/optionModel/option';
import { Media } from 'src/app/models/mediaModel/media';

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  quiz = new Quiz();

  setQuiz(quiz: Quiz) {
    this.quiz = quiz;
    console.log('Quiz set as: ' + this.quiz.quizId);
  }

  getQuiz() {
    return this.quiz;
  }

  getQuizById(quizId: number) {
    throw new Error('Method not implemented.');
  }
  private apiUrl = 'http://localhost:9292'; // Replace 'your-api-url' with your actual API URL

  constructor(private http: HttpClient) {}

  // getAllQuizzes(): Observable<Quiz[]> {
  //   return this.http.get<Quiz[]>(`${this.apiUrl}/quizzes`);
  // }

  getAllQuizzesForUser(userID: number): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.apiUrl}/quizzes/user/${userID}`);
  }

  calculateScore(selectedOptionIds: number[]): Observable<number> {
    return this.http.post<number>(
      `${this.apiUrl}/options/score`,
      selectedOptionIds
    );
  }

  // * Quiz Attempt
  // saveQuizAttempt(
  //   userId: number,
  //   quizId: number,
  //   quizAttempt: QuizAttempt,
  //   optionIds: number[]
  // ): Observable<QuizAttempt> {
  //   return this.http.post<QuizAttempt>(
  //     `${this.apiUrl}/quiz-attempts/save/user/${userId}/quiz/${quizId}`,
  //     { quizAttempt, optionIds }
  //   );
  // }

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

  // * Quiz Attempt with options
  saveQuizAttempt(
    userId: number,
    quizId: number,
    attemptNumber: number,
    score: number,
    optionIds: number[]
  ): Observable<QuizAttempt> {
    const url = `${this.apiUrl}/quiz-attempts/save/user/${userId}/quiz/${quizId}/attempt/${attemptNumber}/score/${score}`;
    return this.http.post<QuizAttempt>(url, optionIds);
  }

  getHighestAttemptNumber(userId: number, quizId: number): Observable<number> {
    const url = `${this.apiUrl}/quiz-attempts/user/${userId}/quiz/${quizId}/highestAttemptNumber`;
    return this.http.get<number>(url);
  }

  getAllQuizzesByCourseId(courseId: number): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.apiUrl}/quizzes/course/${courseId}`);
  }

  saveQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>(`${this.apiUrl}/quizzes/save`, quiz);
  }

  assignCourseToQuiz(quizId: number, courseId: number): Observable<string> {
    return this.http.post<string>(
      `${this.apiUrl}/quizzes/${quizId}/assign-course/${courseId}`,
      null
    );
  }

  saveOption(option: Option): Observable<Option> {
    console.log('Saving option from service' + JSON.stringify(option));
    return this.http.post<Option>(`${this.apiUrl}/options/save`, option);
  }

  assignOptionToQuestion(
    optionId: number,
    questionId: number
  ): Observable<string> {
    return this.http.put<string>(
      `${this.apiUrl}/options/${optionId}/assignToQuestion/${questionId}`,
      {}
    );
  }

  saveMedia(media: Media): Observable<Media> {
    return this.http.post<Media>(`${this.apiUrl}/media/save`, media);
  }

  assignMediaToQuestion(
    mediaId: number,
    questionId: number
  ): Observable<string> {
    return this.http.post<string>(
      `${this.apiUrl}/media/${mediaId}/assignTo/${questionId}`,
      {}
    );
  }

  getQuizAttemptsByQuizId(quizId: number): Observable<QuizAttempt[]> {
    return this.http.get<QuizAttempt[]>(
      `${this.apiUrl}/quiz-attempts/quiz/${quizId}`
    );
  }
}
