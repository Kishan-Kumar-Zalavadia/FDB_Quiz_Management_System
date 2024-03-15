import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/userModel/user';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  constructor(private _http: HttpClient) {}

  public loginUserFromRemote(user: User): Observable<any> {
    return this._http.post<any>('http://localhost:9292/users/login', user);
  }
  // public registraterUserFromRemote(user: User): Observable<any> {
  //   return this._http.post<any>('http://localhost:9292/users/register', user);
  // }

  public registerUserFromRemoteInclusingRole(
    roleID: number,
    user: User
  ): Observable<any> {
    console.log('Role ID: ' + roleID);
    return this._http.post<any>(
      'http://localhost:9292/users/register/withRoleID/' + roleID,
      user
    );
  }
}
