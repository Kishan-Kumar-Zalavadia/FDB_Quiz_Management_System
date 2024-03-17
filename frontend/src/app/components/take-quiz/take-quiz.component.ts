import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { QuizService } from 'src/app/services/quizService/quiz.service';

@Component({
  selector: 'app-take-quiz',
  templateUrl: './take-quiz.component.html',
  styleUrls: ['./take-quiz.component.scss'],
})
export class TakeQuizComponent implements OnInit {
  quiz = new Quiz();
  selectedOptions: { [questionId: number]: number } = {};
  selectedOptionIDs!: number[];
  score: number = 0;

  constructor(
    private route: ActivatedRoute,
    private quizService: QuizService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getQuiz();
    console.log('From Quiz:' + JSON.stringify(this.quiz));
  }

  getQuiz() {
    this.quiz = this.quizService.getQuiz();
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
        this.alert(this.score);
      });
  }

  alert(score: number): void {
    alert('Your quiz score is: ' + score);
    this.router.navigate(['/home']);
  }
}
