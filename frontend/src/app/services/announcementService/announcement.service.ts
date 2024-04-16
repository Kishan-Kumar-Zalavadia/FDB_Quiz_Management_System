import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AnnouncementService {
  private baseUrl = 'http://localhost:9292/announcements'; // Update with your API URL

  constructor(private http: HttpClient) {}

  addAnnouncement(
    announcement: any,
    courseId: number,
    userId: number
  ): Observable<any> {
    return this.http.post(
      `${this.baseUrl}/add/${courseId}/${userId}`,
      announcement
    );
  }
  getAnnouncementsByUserId(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/user/${userId}`);
  }

  getAnnouncementsByUserIdAndCourseId(
    userId: number,
    courseId: number
  ): Observable<any> {
    return this.http.get(`${this.baseUrl}/user/${userId}/course/${courseId}`);
  }

  getAnnouncementsByCourseId(courseId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/course/${courseId}`);
  }
}
