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
  highestAttemptsMap: Map<number, number> = new Map<number, number>(); // Map to store highest attempts for each quiz

  constructor(
    private quizService: QuizService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user = this.userService.getUser();
    this.getQuizzes();
  }

  getQuizzes(): void {
    this.quizService
      .getAllQuizzesForUser(this.user.userID)
      .subscribe((data) => {
        this.quizzes = data;
        // Initialize highestAttemptsMap with default values of 0 for all quizzes
        this.quizzes.forEach((quiz) => {
          this.highestAttemptsMap.set(quiz.quizId, 0);
        });
        // For each quiz, fetch the highest attempt number
        this.quizzes.forEach((quiz) => {
          this.quizService
            .getHighestAttemptNumber(this.user.userID, quiz.quizId)
            .subscribe((highestAttemptNumber) => {
              this.highestAttemptsMap.set(quiz.quizId, highestAttemptNumber);
            });
        });
      });
  }

  takeQuiz(quiz: Quiz): void {
    this.quizService.setQuiz(quiz);
    this.router.navigate(['/quizzes', quiz.quizId]);
  }
  isDisabled(quiz: Quiz): boolean {
    const highestAttempt = this.highestAttemptsMap.get(quiz.quizId);
    return (
      highestAttempt !== undefined &&
      highestAttempt === quiz.quizNumberOfAttempts
    );
  }
}
