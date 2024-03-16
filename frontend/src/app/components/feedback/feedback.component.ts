import { Component } from '@angular/core';
import { Feedback } from 'src/app/models/feedbackModel/feedback';
import { FeedbackService } from 'src/app/services/feedbackService/feedback.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss'],
})
export class FeedbackComponent {
  feedbackText: string = '';
  feedbacks: Feedback[] = [];

  constructor(
    private feedbackService: FeedbackService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.getFeedbacks();
  }

  submitFeedback(): void {
    console.log(
      'User feedback from userID: ' + this.userService.getUser().userID
    );
    if (this.feedbackText.trim() !== '') {
      this.feedbackService
        .saveFeedback(this.feedbackText, this.userService.getUser().userID)
        .subscribe(
          (response) => {
            console.log('Feedback submitted successfully:', response);
            // Reset the feedback text after submission
            this.feedbackText = '';
            this.getFeedbacks();
          },
          (error) => {
            console.error('Error submitting feedback:', error);
          }
        );
    }
  }

  getFeedbacks(): void {
    this.feedbackService.getFeedbacksByUserID(this.userService.getUser().userID).subscribe((feedbacks) => {
      this.feedbacks = feedbacks;
    });
  }
}