import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/profileModel/profile';
import { ProfileService } from 'src/app/services/profileService/profile.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  profileID?: number;
  profile: Profile | undefined;
  constructor(private profileService: ProfileService, private userService: UserService) {}

  ngOnInit(): void {
    this.profileID = this.userService.getUser().profile.profileID;
    this.getProfile(this.profileID);
  }

  getProfile(profileID: number): void {
    this.profileService.getProfileByProfileId(profileID).subscribe(
      (data) => {
        this.profile = data;
        console.log(data);
      },
      (error) => {
        console.log('Error');
      }
    );
  }
}
