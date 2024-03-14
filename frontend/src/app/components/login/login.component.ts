import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/userModel/user';
import { RegisterService } from 'src/app/services/registerService/register.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  user = new User();
  errorMsg = '';
  constructor(
    private _service: RegisterService,
    private _router: Router,
    private _userService: UserService
  ) {}

  ngOnInit(): void {}

  loginUser() {
    this._service.loginUserFromRemote(this.user).subscribe(
      (data) => {
        console.log(data);
        // this._userService.setUser(data);
        // if (this.user.emailId == 'admin@gmail.com') {
        //   this._router.navigate(['/home/allUsers']);
        // } else this._router.navigate(['/home/buy']);
        // this._router.navigate(["/home"], {
        //   skipLocationChange: true,
        // });
      },
      (error) => {
        console.log('Exception Occured:' + error);
        this.errorMsg = 'Bad Credentials, please try again';
      }
    );
  }

  gotoRegistration() {
    this._router.navigate(['/register']);
  }
}
