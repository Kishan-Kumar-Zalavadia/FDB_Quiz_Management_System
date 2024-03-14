import { Profile } from '../profileModel/profile';
import { Role } from '../roleModel/role';

export class User {
  userID!: number;
  emailID!: string;
  userName!: string;
  password!: string;
  profileID!: number;
  roleID!: number;
  profile!: Profile
  role!: Role
}
