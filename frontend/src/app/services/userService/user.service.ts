import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuizAttempt } from 'src/app/models/attemptModel/attempt';
import { User } from 'src/app/models/userModel/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  currentUser = new User();

  setUser(user: User) {
    console.log('User set as: ' + user.userID);
    this.currentUser = user;
  }
  getUser() {
    return this.currentUser;
  }

  constructor(private _http: HttpClient) {}

  private usersUrl = 'http://localhost:9292/users';

  public getUserByIDFromRemote(userID: number): Observable<any> {
    const url = `${this.usersUrl}/${userID}`;
    return this._http.get<any>(url);
  }

  // //* ------------------------------------------------------------------------------------------------
  // // * For ADMIN ONLY
  // private apiUrl = 'http://localhost:9292/users';

  // // Get all users
  // getAllUsers(): Observable<User[]> {
  //   return this._http.get<User[]>(this.apiUrl);
  // }

  // // Update user
  // updateUserDetails(updatedUser: User): Observable<User> {
  //   const url = `${this.apiUrl}/update/${updatedUser.id}`;
  //   return this._http.put<User>(url, updatedUser);
  // }

  // Method to fetch quiz attempts by user ID
  getQuizAttemptsByUserId(userId: number): Observable<QuizAttempt[]> {
    return this._http.get<QuizAttempt[]>(
      `${this.usersUrl}/${userId}/quiz-attempts`
    );
  }
}
