import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/models/courseModel/course';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private apiUrl = 'http://localhost:9292';

  constructor(private http: HttpClient) {}

  enrollCourse(userId: number, course: Course): Observable<any> {
    console.log('Enrolling Course' + JSON.stringify(course));
    return this.http.post<any>(`${this.apiUrl}/users/${userId}/enroll`, course);
  }

  getEnrolledCourses(userId: number): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/users/${userId}/courses`);
  }

  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/courses`);
  }

  getUnenrolledCourses(userId: number): Observable<Course[]> {
    return this.http.get<Course[]>(
      `${this.apiUrl}/courses/not-enrolled/${userId}`
    );
  }
}
