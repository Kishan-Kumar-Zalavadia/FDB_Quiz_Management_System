import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Profile } from 'src/app/models/profileModel/profile';
import { DepartmentService } from 'src/app/services/departmentService/department.service';
import { ProfileService } from 'src/app/services/profileService/profile.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  profileID!: number;
  // profile!: Profile;
  // profileForm!: Profile;

  profile = new Profile();
  profileForm = new Profile();

  constructor(
    private profileService: ProfileService,
    private userService: UserService,
  ) {}

  ngOnInit(): void {
    this.profileID = this.userService.getUser().profile.profileID;
    this.getProfile(this.profileID);
    this.profileForm = this.profile;
    console.log('Current ProfileID:' + this.profile.profileID);
  }

  getProfile(profileID: number): void {
    this.profileService.getProfileByProfileId(profileID).subscribe(
      (data) => {
        this.profile = data;
        // this.profileForm = data;
        console.log(data);
      },
      (error) => {
        console.log('Error');
      }
    );
  }

  updateProfile(profileForm: NgForm): void {
    if (profileForm.invalid) {
      return;
    }

    const updatedProfile: Profile = profileForm.value;
    console.log('Update Profile');
    console.log(updatedProfile.profileID);
    this.profileService.updateProfile(updatedProfile, this.profileID).subscribe(
      () => {
        // Handle successful update
        this.getProfile(this.profileID);
        console.log('Profile updated successfully');
      },
      (error) => {
        // Handle error
        console.error('Error updating profile:', error);
      }
    );
    this.closeAdminPopup();
  }

  // * Pop up

  showAdminPopup: boolean = false;

  editProfile(profile: Profile) {
    this.profileForm = { ...profile };
    this.openAdminPopup();
  }

  openAdminPopup() {
    this.showAdminPopup = true;
  }

  // Method to close the admin pop-up
  closeAdminPopup() {
    this.showAdminPopup = false;
  }
}
