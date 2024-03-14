import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/userModel/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  user = new User();

  setUser(user: User) {
    this.user = user;
  }
  getUser() {
    return this.user;
  }

  seller = new User();

  setSeller(seller: User) {
    this.seller = seller;
  }

  getSeller() {
    return this.seller;
  }

  constructor(private _http: HttpClient) {}

  private usersUrl = 'http://localhost:9292/users';

  public getUserByIDFromRemote(userID: number): Observable<any> {
    const url = `${this.usersUrl}/${userID}`;
    console.log("From userService: "+userID);
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
}