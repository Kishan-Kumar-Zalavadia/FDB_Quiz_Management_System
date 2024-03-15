import { User } from "../userModel/user";

export class Course {
  courseId!: number;
  courseName!: string;
  users!: User[];
}
