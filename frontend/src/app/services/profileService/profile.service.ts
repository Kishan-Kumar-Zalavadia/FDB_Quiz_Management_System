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
  private apiUrl = 'http://localhost:9292/profiles';

  // * Get Profile
  getProfileByProfileId(profileId: number): Observable<any> {
    return this.http.get<Profile>(`${this.baseUrl}/${profileId}`);
  }

  // * Update profile
  updateProfile(profile: Profile, profileID: number): Observable<any> {
    const updateUrl = `${this.apiUrl}/update/${profileID}`; // Construct URL with profile ID
    return this.http.put(updateUrl, profile); // Send PUT request to update profile
  }
}
