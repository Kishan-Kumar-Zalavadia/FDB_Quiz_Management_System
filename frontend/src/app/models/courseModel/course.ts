import { Department } from '../departmentModel/department';
import { User } from '../userModel/user';

export class Course {
  courseId!: number;
  courseName!: string;
  users!: User[];
  departments!: Department[];
  professor!: User;
}
