import { Component, OnInit } from '@angular/core';
import { QuizAttempt } from 'src/app/models/attemptModel/attempt';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-attempts',
  templateUrl: './attempts.component.html',
  styleUrls: ['./attempts.component.scss'],
})
export class AttemptsComponent implements OnInit {
  quizAttempts: QuizAttempt[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.fetchQuizAttempts();
  }

  fetchQuizAttempts(): void {
    // Assuming you have a method in your UserService to fetch quiz attempts by user ID
    this.userService.getQuizAttemptsByUserId(this.userService.getUser().userID).subscribe(
      (quizAttempts: QuizAttempt[]) => {
        this.quizAttempts = quizAttempts;
      },
      (error) => {
        console.error('Error fetching quiz attempts:', error);
      }
    );
  }
}
