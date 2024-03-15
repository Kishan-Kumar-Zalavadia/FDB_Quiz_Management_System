import { Department } from "../departmentModel/department";

export class Profile {
  profileID!: number;
  firstName!: string;
  lastName!: string;
  phoneNumber!: number;
  photoURL!: string;
  streetNumber!: string;
  streetName!: string;
  aptNumber!: string;
  city!: string;
  state!: string;
  zip!: string;
  country!: string;
  dob!: Date;
  age!: number;
  
  department !: Department
}
