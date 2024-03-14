import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/profileModel/profile';
import { ProfileService } from 'src/app/services/profileService/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  profile: Profile | undefined;
  noProfile: boolean = false;
  constructor(private profileService: ProfileService) {}

  ngOnInit(): void {
    this.getProfile(1);
  }

  getProfile(profileID: number): void {
    this.profileService.getProfileByProfileId(profileID).subscribe(
      (profile: Profile) => {
        this.profile = profile;
      },
      (error) => {
        console.log('Error');
        this.noProfile = true;
      }
    );
  }
}
