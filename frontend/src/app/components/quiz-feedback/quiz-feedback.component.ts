import { Component } from '@angular/core';
import { QuizFeedback } from 'src/app/models/quizFeedbackModel/quiz-feedback';
import { QuizFeedbackService } from 'src/app/services/quizFeedbackService/quiz-feedback.service';
import { QuizService } from 'src/app/services/quizService/quiz.service';

@Component({
  selector: 'app-quiz-feedback',
  templateUrl: './quiz-feedback.component.html',
  styleUrls: ['./quiz-feedback.component.scss'],
})
export class QuizFeedbackComponent {
  quizFeedbacks: QuizFeedback[] = [];

  constructor(
    private feedbackService: QuizFeedbackService,
    private quizService: QuizService
  ) {}

  quizId!: number;

  ngOnInit(): void {
    this.quizId = this.quizService.getQuiz().quizId;

    this.loadQuizFeedbacks();
  }

  loadQuizFeedbacks(): void {
    this.feedbackService.getQuizFeedbacksByQuizId(this.quizId).subscribe(
      (feedbacks) => {
        this.quizFeedbacks = feedbacks;
      },
      (error) => {
        console.error('Error fetching quiz feedbacks:', error);
        // Handle error appropriately
      }
    );
  }
}
