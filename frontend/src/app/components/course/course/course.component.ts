import { Component } from '@angular/core';
import { Course } from 'src/app/models/courseModel/course';
import { CourseService } from 'src/app/services/courseService/course.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss'],
})
export class CourseComponent {
  courses: Course[] = [];
  enrolledCourses: Course[] = [];
  canEnrollCourses: Course[] = [];
  userID!: number;

  constructor(
    private courseService: CourseService,
    private userService: UserService
  ) {
    this.userID = this.userService.getUser().userID;
  }

  ngOnInit(): void {
    this.getEnrolledCourses();
    this.getAllCourses();
  }

  // getAllCourses(): void {
  //   this.courseService.getAllCourses().subscribe((courses) => {
  //     this.courses = courses;
  //   });

  //   this.canEnrollCourses = this.courses.filter((course) => {
  //     return !this.enrolledCourses.some(
  //       (enrolledCourse) => enrolledCourse.courseId === course.courseId
  //     );
  //   });
  //   console.log("Can Enroll Courses: "+JSON.stringify(this.canEnrollCourses));
  // }

  getAllCourses(): void {
    // this.courseService.getAllCourses().subscribe((courses) => {
    //   this.courses = courses;
    // });
    this.courseService
      .getUnenrolledCourses(this.userID)
      .subscribe((courses) => {
        this.courses = courses;
      });
  }

  updateCanEnrollCourses(): void {
    if (this.courses && this.enrolledCourses) {
      this.canEnrollCourses = this.courses.filter(
        (course) =>
          !this.enrolledCourses.some(
            (enrolledCourse) => enrolledCourse.courseId === course.courseId
          )
      );
      this.getEnrolledCourses();
      console.log(
        'Enrolled Courses in update: ' + JSON.stringify(this.enrollCourse)
      );
      console.log(
        'Can Enroll Courses in update: ' + JSON.stringify(this.canEnrollCourses)
      );
    }
  }

  getEnrolledCourses(): void {
    this.courseService.getEnrolledCourses(this.userID).subscribe(
      (data) => {
        this.enrolledCourses = data;
        console.log(
          'Enrolled Courses: ' + JSON.stringify(this.enrolledCourses)
        );
      },
      (error) => {
        console.log('Error while getting enrolled courses:' + error);
      }
    );
  }

  enrollCourse(course: Course): void {
    console.log('Course :', course);
    this.courseService.enrollCourse(this.userID, course).subscribe(
      () => {
        this.getEnrolledCourses();
        this.getAllCourses();
      },
      (error) => {
        console.log('Error while enrolling:' + error);
        this.getEnrolledCourses();
        this.getAllCourses();
      }
    );
  }
}
