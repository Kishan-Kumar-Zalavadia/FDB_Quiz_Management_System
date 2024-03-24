import { Component } from '@angular/core';
import { Course } from 'src/app/models/courseModel/course';
import { CourseService } from 'src/app/services/courseService/course.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss'],
})
export class AddCourseComponent {
  course = new Course();
  userID!: number;
  constructor(
    private courseService: CourseService,
    private userService: UserService
  ) {
    this.userID = this.userService.getUser().userID;
  }
  ngOnInit(): void {}

  addCourse(): void {
    if (!this.course.courseName.trim()) {
      return;
    }
    this.course.professor = this.userService.getUser();
    this.courseService.saveCourse(this.course).subscribe((savedCourse) => {
      console.log('Course added:', savedCourse);
      this.course = new Course();
    });
  }
}
