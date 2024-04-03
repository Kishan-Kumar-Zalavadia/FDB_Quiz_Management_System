import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/userModel/user';
import { RegisterService } from 'src/app/services/registerService/register.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  constructor(
    private _service: RegisterService,
    private _router: Router,
    private _userService: UserService
  ) {}

  user = new User();

  ngOnInit(): void {
    this.user = this._userService.getUser();

    // ! Remove this line to eliminate default user
    this.loginUser(3);
    // if (this.user.userID === undefined) this._router.navigate(['/login']);
  }

  loginUser(userID: number) {
    this._userService.getUserByIDFromRemote(userID).subscribe(
      (data) => {
        // console.log(data);
        console.log('Default user logged in');
        this.user = data;
        this._userService.setUser(data);
      },
      (error) => {}
    );
  }
}
