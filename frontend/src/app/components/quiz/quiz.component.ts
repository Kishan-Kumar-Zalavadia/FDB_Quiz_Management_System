import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { User } from 'src/app/models/userModel/user';
import { QuizService } from 'src/app/services/quizService/quiz.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss'],
})
export class QuizComponent implements OnInit {
  quizzes: Quiz[] = [];
  user = new User();

  constructor(
    private quizService: QuizService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user = this.userService.getUser();
    this.getQuizzes();
  }

  // getQuizzes(): void {
  //   this.quizService.getAllQuizzes().subscribe((data) => (this.quizzes = data));
  // }

  getQuizzes(): void {
    this.quizService
      .getAllQuizzesForUser(this.user.userID)
      .subscribe((data) => (this.quizzes = data));
  }

  takeQuiz(quiz: Quiz): void {
    this.quizService.setQuiz(quiz);
    console.log('Quiz set to ' + quiz.quizId);
    this.router.navigate(['/quizzes', quiz.quizId]);
  }
}
