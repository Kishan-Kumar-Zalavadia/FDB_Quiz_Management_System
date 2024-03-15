import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FeedbackService {
  private apiUrl: string = 'http://localhost:9292/feedbacks'; // Update with your API URL

  constructor(private http: HttpClient) {}

  saveFeedback(feedbackText: string, userID: number): Observable<any> {
    const feedbackData = { feedbackText: feedbackText };
    return this.http.post<any>(`${this.apiUrl}/save/user/${userID}`, feedbackData);
  }
}
