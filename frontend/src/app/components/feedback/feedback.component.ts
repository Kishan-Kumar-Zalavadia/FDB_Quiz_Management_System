import { Component } from '@angular/core';
import { FeedbackService } from 'src/app/services/feedbackService/feedback.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss'],
})
export class FeedbackComponent {
  feedbackText: string = '';

  constructor(private feedbackService: FeedbackService, private userService: UserService) {}

  ngOnInit(): void {}

  submitFeedback(): void {
    console.log("User feedback from userID: "+this.userService.getUser().userID);
    if (this.feedbackText.trim() !== '') {
      this.feedbackService
        .saveFeedback(this.feedbackText, this.userService.getUser().userID)
        .subscribe(
          (response) => {
            console.log('Feedback submitted successfully:', response);
            // Reset the feedback text after submission
            this.feedbackText = '';
          },
          (error) => {
            console.error('Error submitting feedback:', error);
          }
        );
    }
  }
}