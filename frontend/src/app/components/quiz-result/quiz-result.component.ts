import { Component } from '@angular/core';
import { QuizAttempt } from 'src/app/models/attemptModel/attempt';
import { User } from 'src/app/models/userModel/user';
import { QuizService } from 'src/app/services/quizService/quiz.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-quiz-result',
  templateUrl: './quiz-result.component.html',
  styleUrls: ['./quiz-result.component.scss']
})
export class QuizResultComponent {
  quizAttemptsUsers: User[] = [];

  constructor(private quizService: QuizService, private userService: UserService) { }

  ngOnInit(): void {
    this.loadQuizAttempts();
  }
loadQuizAttempts() {

    this.userService
      .getUsersWithFilteredQuizAttempts(this.quizService.getQuiz().quizId)
      .subscribe(
        (data) => {
          this.quizAttemptsUsers = data;
          console.log(JSON.stringify(this.quizAttemptsUsers));
        },
        (error) => {
          console.log('Error fetching quiz attempts:', error);
        }
      );
  }
}


// import { Component } from '@angular/core';
// import { QuizAttempt } from 'src/app/models/attemptModel/attempt';
// import { User } from 'src/app/models/userModel/user';
// import { QuizService } from 'src/app/services/quizService/quiz.service';
// import { UserService } from 'src/app/services/userService/user.service';

// @Component({
//   selector: 'app-quiz-result',
//   templateUrl: './quiz-result.component.html',
//   styleUrls: ['./quiz-result.component.scss'],
// })
// export class QuizResultComponent {
//   quizAttemptsUsers: User[] = [];
//   allAttempts: QuizAttempt[] = [];

//   constructor(
//     private quizService: QuizService,
//     private userService: UserService
//   ) {}

//   ngOnInit(): void {
//     this.loadQuizAttempts();
//   }

//   loadQuizAttempts() {
//     this.userService
//       .getUsersWithFilteredQuizAttempts(this.quizService.getQuiz().quizId)
//       .subscribe(
//         (data) => {
//           this.quizAttemptsUsers = data;
//           this.extractAllAttempts();
//           console.log(JSON.stringify(this.quizAttemptsUsers));
//         },
//         (error) => {
//           console.log('Error fetching quiz attempts:', error);
//         }
//       );
//   }

//   extractAllAttempts() {
//     this.allAttempts = [];
//     for (const user of this.quizAttemptsUsers) {
//       console.log("LOOP")
//       console.log(user.quizAttempt)
//       if (user.quizAttempt && Array.isArray(user.quizAttempt)) {
//         this.allAttempts.push(...user.quizAttempt);
//       }
//     }
//   }
// }