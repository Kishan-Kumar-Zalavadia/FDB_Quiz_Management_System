import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/questionModel/question';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { QuestionService } from 'src/app/services/questionService/question.service';
import { QuizService } from 'src/app/services/quizService/quiz.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.scss'],
})
export class AddQuestionComponent {
  constructor(
    private router: Router,
    private quizService: QuizService,
    private questionService: QuestionService
  ) {}

  quiz: Quiz = new Quiz();
  questions: Question[] = [];

  ngOnInit(): void {
    this.quiz = this.quizService.getQuiz();
    this.loadQuestions();
  }
  loadQuestions(): void {
    this.questionService.getAllQuestionsByQuizId(this.quiz.quizId).subscribe(
      (questions) => {
        this.questions = questions;
      },
      (error) => {
        console.error('Error fetching questions:', error);
      }
    );
  }

  hasOptions(question: Question): boolean {
    return question.options && question.options.length > 0;
  }

  hasMedia(question: Question): boolean {
    return question.mediaList && question.mediaList.length > 0;
  }
}
