import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizAttempt } from 'src/app/models/attemptModel/attempt';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { Result } from 'src/app/models/resultModel/result';
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
  highestAttemptNumber: number = 0;

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
    this.getHighestAttemptNumber();
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
  }

  calculateScore(): void {
    this.quizService
      .calculateScore(this.selectedOptionIDs)
      .subscribe((score) => {
        this.score = score;
        console.log('Score:', this.score);
        this.saveQuizAttempt(score);
        this.alert(this.score);
      });

  }

  alert(score: number): void {
    alert('Your quiz score is: ' + score);
    this.router.navigate(['/home']);
  }

  getHighestAttemptNumber(): void {
    this.quizService
      .getHighestAttemptNumber(this.user.userID, this.quiz.quizId)
      .subscribe(
        (highestAttemptNumber) => {
          this.highestAttemptNumber = highestAttemptNumber;
          console.log('Highest attempt number:', highestAttemptNumber);
          // Handle success response
        },
        (error) => {
          // console.error('Error fetching highest attempt number:', error);
          this.highestAttemptNumber=0;
          console.log('Highest attempt number:', this.highestAttemptNumber);

          // Handle error response
        }
      );
  }

  saveQuizAttempt(score: number): void {
    const currentTime = new Date(); // Assuming you want to set the current time as startTime and endTime
    console.log('Saving quiz score'+score)
    // Create a new QuizAttempt object
    // const quizAttempt: QuizAttempt = {
    //   quizAttemptId: 0, // This value will be assigned by the backend upon creation
    //   attemptNumber: 1, // Assuming attemptNumber starts from 1


    //   // startTime: new Date().getTime(),
    //   // endTime: ,
    //   attemptDate: new Date(), // Set attemptDate to current date

    //   user: this.userService.getUser(),
    //   quiz: this.quizService.getQuiz(),
    //   selectedOptions: [],
    //   result: new Result
    // };

    this.quizService
      .saveQuizAttempt(
        this.user.userID,
        this.quiz.quizId,
        this.highestAttemptNumber + 1,
        score,
        this.selectedOptionIDs,
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
