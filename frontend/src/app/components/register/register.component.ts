import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from 'src/app/models/roleModel/role';
import { User } from 'src/app/models/userModel/user';
import { RegisterService } from 'src/app/services/registerService/register.service';
import { RoleService } from 'src/app/services/roleService/role.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  user = new User();
  msg = '';
  roles: Role[] = [];
  selectedRole?: Role;

  constructor(
    private registerService: RegisterService,
    private roleService: RoleService,
    private _router: Router
  ) {}

  ngOnInit() {
    this.getAllRoles();
  }

  // registerUser(RegistrationForm: any) {
  //   console.log(RegistrationForm.role);
  //   // this.user.role = this.selectedRole;
  //   this.registerService.registraterUserFromRemote(this.user).subscribe(
  //     (data) => {
  //       console.log('Responce recived' + this.user.userID);
  //       this.msg = 'Registration Successful';
  //     },
  //     (error) => {
  //       console.log('Exception occured');
  //       // this.msg=error.msg;
  //       this.msg = 'User Already Exists';
  //     }
  //   );
  // }

  registerUser(RegistrationForm: any) {
    console.log(RegistrationForm.role);
    // this.user.role = this.selectedRole;
    this.registerService
      .registerUserFromRemoteInclusingRole(this.selectedRole!.roleID,this.user)
      .subscribe(
        (data) => {
          console.log('Responce recived' + this.user.userID);
          this.msg = 'Registration Successful';
        },
        (error) => {
          console.log('Exception occured');
          // this.msg=error.msg;
          this.msg = 'User Already Exists';
        }
      );
  }

  getAllRoles() {
    this.roleService.getAllRoles().subscribe(
      (data) => {
        console.log('All Roles: ' + JSON.stringify(data));
        this.roles = data;
      },
      (error) => {
        console.log('Error');
      }
    );
  }

  logSelectedRole() {
    console.log('Selected Role:', this.selectedRole?.roleType);
  }

  compareRoles(role1: Role, role2: Role): boolean {
    return role1 && role2 ? role1.roleID === role2.roleID : role1 === role2;
  }
}
