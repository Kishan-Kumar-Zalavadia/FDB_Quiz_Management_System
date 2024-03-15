import { Course } from '../courseModel/course';
import { Profile } from '../profileModel/profile';
import { Role } from '../roleModel/role';

export class User {
  userID!: number;
  emailID!: string;
  userName!: string;
  password!: string;
  profile!: Profile
  role!: Role

  courses!: Course[];;
}
