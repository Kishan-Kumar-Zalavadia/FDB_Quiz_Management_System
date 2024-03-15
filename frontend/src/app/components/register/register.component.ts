import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Department } from 'src/app/models/departmentModel/department';
import { Role } from 'src/app/models/roleModel/role';
import { User } from 'src/app/models/userModel/user';
import { DepartmentService } from 'src/app/services/departmentService/department.service';
import { ProfileService } from 'src/app/services/profileService/profile.service';
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
  departments: Department[] = [];
  selectedRole?: Role;
  selectedDepartment?: Department;

  constructor(
    private registerService: RegisterService,
    private roleService: RoleService,
    private departmentService: DepartmentService,
    private profileService: ProfileService,
    private _router: Router
  ) {}

  ngOnInit() {
    this.getAllRoles();
    this.getAllDepartments();
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
    // this.user.role = this.selectedRole;
    this.registerService
      .registerUserFromRemoteInclusingRoleAndDepartment(
        this.selectedRole!.roleID,
        this.selectedDepartment!.departmentID,
        this.user
      )
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

  getAllDepartments() {
    this.departmentService.getAllDepartments().subscribe(
      (data) => {
        console.log('All Departments: ' + JSON.stringify(data));
        this.departments = data;
      },
      (error) => {
        console.log('Error');
      }
    );
  }

  logSelectedRole() {
    console.log('Selected Role:', this.selectedRole?.roleType);
  }

  logSelectedDepartment() {
    console.log('Selected Department:');
    console.log(JSON.stringify(this.selectedDepartment));
  }

  compareRoles(role1: Role, role2: Role): boolean {
    return role1 && role2 ? role1.roleID === role2.roleID : role1 === role2;
  }

  compareDepartments(
    department1: Department,
    department2: Department
  ): boolean {
    return department1 && department2
      ? department1.departmentID === department2.departmentID
      : department1 === department2;
  }
}
