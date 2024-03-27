import { Component } from '@angular/core';
import { Router } from '@angular/router';
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
  constructor(
    private router: Router,
    private quizService: QuizService,
    private courseService: CourseService
  ) {}

  quizzes: Quiz[] = [];
  quiz: Quiz = new Quiz();
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

  saveQuiz(): void {
    // Set the createdDate to today's date
    this.quiz.createdDate = new Date();
    // this.assignCourseToQuiz();
    this.quizService.saveQuiz(this.quiz).subscribe(
      (savedQuiz) => {
        // Reset the form after successful submission
        this.quiz = savedQuiz;
        this.assignCourseToQuiz(savedQuiz.quizId, this.course.courseId);
        alert('Quiz saved successfully!');
        this.loadQuizzes();
      },
      (error) => {
        console.error('Error saving quiz:', error);
        alert('Failed to save quiz. Please try again.');
      }
    );
  }

  assignCourseToQuiz(quizId: number, courseId: number): void {
    this.quizService.assignCourseToQuiz(quizId, courseId).subscribe(
      (response) => {
        console.log(response); // Handle success response
      },
      (error) => {
        console.error(error); // Handle error response
      }
    );
  }

  redirectToAddQuestions(quiz: Quiz): void {
    this.quizService.setQuiz(quiz);
    this.router.navigate(['/home/addQuestions', quiz.quizId]);
  }
}
