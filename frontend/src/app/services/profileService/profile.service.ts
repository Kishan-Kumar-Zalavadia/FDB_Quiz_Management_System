import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from 'src/app/models/profileModel/profile';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  private baseUrl = 'http://localhost:9292/profiles'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getProfileByProfileId(profileId: number): Observable<any> {
    return this.http.get<Profile>(`${this.baseUrl}/${profileId}`);
  }
}
