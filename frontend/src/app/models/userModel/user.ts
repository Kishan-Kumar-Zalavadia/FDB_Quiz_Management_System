import { Profile } from '../profileModel/profile';

export class User {
  userID!: number;
  emailID!: string;
  userName!: string;
  password!: string;
  profileID!: number;
  roleID!: number;
  profile!: Profile
}
