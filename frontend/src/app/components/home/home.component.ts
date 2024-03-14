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
  }
}
