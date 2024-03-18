import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizAttempt } from 'src/app/models/attemptModel/attempt';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { User } from 'src/app/models/userModel/user';
import { QuizService } from 'src/app/services/quizService/quiz.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-take-quiz',
  templateUrl: './take-quiz.component.html',
  styleUrls: ['./take-quiz.component.scss'],
})
export class TakeQuizComponent implements OnInit {
  quiz = new Quiz();
  user = new User();
  selectedOptions: { [questionId: number]: number } = {};
  selectedOptionIDs!: number[];
  score: number = 0;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private quizService: QuizService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getQuiz();
    this.getUser();
    console.log('From Quiz:' + this.quiz.quizId);
  }
  

  getQuiz() {
    this.quiz = this.quizService.getQuiz();
  }
  getUser() {
    this.user = this.userService.getUser();
  }

  submitQuiz(): void {
    console.log('Selected Options:', this.selectedOptions);
    this.selectedOptionIDs = Object.values(this.selectedOptions);
    console.log('Selected Option IDs:', this.selectedOptionIDs);
    this.calculateScore();
    this.saveQuizAttempt();
  }

  calculateScore(): void {
    this.quizService
      .calculateScore(this.selectedOptionIDs)
      .subscribe((score) => {
        this.score = score;
        console.log('Score:', this.score);
        this.alert(this.score);
      });
  }

  alert(score: number): void {
    alert('Your quiz score is: ' + score);
    this.router.navigate(['/home']);
  }

  saveQuizAttempt(): void {
    const currentTime = new Date(); // Assuming you want to set the current time as startTime and endTime

    // Create a new QuizAttempt object
    const quizAttempt: QuizAttempt = {
      quizAttemptId: 0, // This value will be assigned by the backend upon creation
      attemptNumber: 1, // Assuming attemptNumber starts from 1
      // startTime: new Date().getTime(),
      // endTime: ,
      attemptDate: new Date(), // Set attemptDate to current date

      user: this.userService.getUser(),
      quiz: this.quizService.getQuiz(),
      selectedOptions: [],
    };

    // this.quizService
    //   .saveQuizAttempt(
    //     this.user.userID,
    //     this.quiz.quizId,
    //     quizAttempt,
    //     this.selectedOptionIDs
    //   )
    //   .subscribe(
    //     (savedAttempt) => {
    //       console.log('Quiz attempt saved:', savedAttempt);
    //       this.calculateScore();
    //     },
    //     (error) => {
    //       console.error('Error saving quiz attempt:', error);
    //     }
    //   );
    this.quizService
      .saveQuizAttempt(
        this.user.userID,
        this.quiz.quizId,
        4,
        this.selectedOptionIDs
      )
      .subscribe(
        (savedAttempt) => {
          console.log('Quiz attempt saved:', savedAttempt);
          // Handle success response
        },
        (error) => {
          console.error('Error saving quiz attempt:', error);
          // Handle error response
        }
      );
  }
}
