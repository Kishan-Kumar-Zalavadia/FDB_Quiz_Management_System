// department.model.ts

import { Course } from "../courseModel/course";

export class Department {
  departmentID!: number;
  departmentName!: string;
  courses!: Course[];
}
