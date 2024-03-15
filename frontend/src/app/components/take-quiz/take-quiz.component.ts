import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { QuizService } from 'src/app/services/quizService/quiz.service';

@Component({
  selector: 'app-take-quiz',
  templateUrl: './take-quiz.component.html',
  styleUrls: ['./take-quiz.component.scss'],
})
export class TakeQuizComponent implements OnInit {
  quiz = new Quiz();

  constructor(
    private route: ActivatedRoute,
    private quizService: QuizService
  ) {}

  ngOnInit(): void {
    this.getQuiz();
    console.log("From Quiz:"+JSON.stringify(this.quiz));
  }

  getQuiz(){
    this.quiz = this.quizService.getQuiz();
  }

}
