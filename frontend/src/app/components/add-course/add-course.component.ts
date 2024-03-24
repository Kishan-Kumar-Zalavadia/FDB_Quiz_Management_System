import { Component } from '@angular/core';
import { Course } from 'src/app/models/courseModel/course';
import { Department } from 'src/app/models/departmentModel/department';
import { CourseService } from 'src/app/services/courseService/course.service';
import { DepartmentService } from 'src/app/services/departmentService/department.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss'],
})
export class AddCourseComponent {
  course: Course = new Course();
  departments: Department[] = [];
  selectedDepartments: number[] = [];
  myCourses: Course[] = [];

  constructor(
    private courseService: CourseService,
    private departmentService: DepartmentService,
    private userService: UserService
  ) {}
  ngOnInit(): void {
    this.loadDepartments();
    this.getAllCoursesByProfessorId();
  }
  loadDepartments(): void {
    this.departmentService.getAllDepartments().subscribe((departments) => {
      this.departments = departments;
    });
  }

  toggleSelection(departmentId: number): void {
    const index = this.selectedDepartments.indexOf(departmentId);
    if (index === -1) {
      // Department not in the array, add it
      this.selectedDepartments.push(departmentId);
    } else {
      // Department already in the array, remove it
      this.selectedDepartments.splice(index, 1);
    }
  }

  addCourse(): void {
    if (!this.course.courseName.trim()) {
      return;
    }
    this.course.professor = this.userService.getUser();
    this.course.departments = this.departments.filter((department) =>
      this.selectedDepartments.includes(department.departmentID)
    );
    this.courseService.saveCourse(this.course).subscribe((savedCourse) => {
      console.log('Course added:', savedCourse);
      this.course = savedCourse;

      this.assignDepartmentsToCourse(this.course.courseId);
      this.getAllCoursesByProfessorId();
      this.course = new Course();
    });

  }

  assignDepartmentsToCourse(courseID: number): void {
    this.courseService
      .assignDepartmentsToCourse(courseID, this.selectedDepartments)
      .subscribe
      // (response) => console.log(response),
      // (error) => console.error(error)
      ();
  }

  getAllCoursesByProfessorId(): void {
    this.courseService
      .getAllCoursesByProfessorId(this.userService.getUser().userID)
      .subscribe(
        (courses) => {
          this.myCourses = courses;
        },
        (error) => {
          console.error('Error fetching courses:', error);
        }
      );
  }
}
