import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { QuizService } from 'src/app/services/quizService/quiz.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss'],
})
export class QuizComponent implements OnInit {
  quizzes: Quiz[] = [];

  constructor(private quizService: QuizService, private router: Router) {}

  ngOnInit(): void {
    this.getQuizzes();
  }

  getQuizzes(): void {
    this.quizService.getAllQuizzes().subscribe((data) => (this.quizzes = data));
  }

  takeQuiz(quiz: Quiz): void {
    this.quizService.setQuiz(quiz);
    console.log('Quiz set to ' + quiz.quizId);
    this.router.navigate(['/quizzes', quiz.quizId]);
  }
}
