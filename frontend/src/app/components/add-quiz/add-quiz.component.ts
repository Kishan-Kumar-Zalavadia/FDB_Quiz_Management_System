import { Component } from '@angular/core';
import { Course } from 'src/app/models/courseModel/course';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { CourseService } from 'src/app/services/courseService/course.service';
import { QuizService } from 'src/app/services/quizService/quiz.service';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.scss'],
})
export class AddQuizComponent {
  constructor(private quizService: QuizService, private courseService: CourseService) {}

  quizzes: Quiz[] = [];
  course = new Course();
  ngOnInit(): void {
    this.course = this.courseService.getCourse();
    this.loadQuizzes();
  }

  loadQuizzes(): void {
    this.quizService.getAllQuizzesByCourseId(this.course.courseId).subscribe(
      (quizzes: Quiz[]) => {
        this.quizzes = quizzes;
      },
      (error) => {
        console.error('Error fetching quizzes:', error);
      }
    );
  }
}
