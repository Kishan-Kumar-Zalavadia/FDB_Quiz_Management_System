import { Course } from "../courseModel/course";
import { User } from "../userModel/user";

export class Announcement {
  announcementId!: number;
  content!: string;
  date!: string; 
  time!: string;
  course!: Course; 
  user!: User; 
}
