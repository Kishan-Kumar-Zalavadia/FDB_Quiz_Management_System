import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/questionModel/question';
import { Quiz } from 'src/app/models/quizModel/quiz';
import { Option } from 'src/app/models/optionModel/option';
import { Media } from 'src/app/models/mediaModel/media';
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
  msg: string = '';

  ngOnInit(): void {
    this.quiz = this.quizService.getQuiz();
    this.loadQuestions();
  }
  loadQuestions(): void {
    console.log('Loading Questions...');
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

  // openPopup(): void {
  //   const userInput = prompt('Enter your message:');
  //   if (userInput !== null) {
  //     this.msg = userInput;
  //     console.log('Msg:' + this.msg);
  //   } else {
  //     // User clicked Cancel
  //     console.log('User cancelled the prompt.');
  //   }
  // }

  // ********************************************************************************************************************************
  // * Question
  showQuestionPopup: boolean = false; // Variable to control the visibility of the popup
  newQuestion: Question = new Question(); // Variable to store the new question data

  openQuestionPopup(): void {
    this.showQuestionPopup = true;
  }

  closeQuestionPopup(): void {
    this.showQuestionPopup = false;
  }

  // saveQuestion(): void {
  //   console.log('New question:', this.newQuestion);
  //   this.newQuestion = new Question();
  //   this.closeQuestionPopup();
  // }

  saveQuestion(): void {
    this.questionService.saveQuestion(this.newQuestion).subscribe(
      (savedQuestion) => {
        this.assignQuestionToQuiz(savedQuestion.questionId, this.quiz.quizId);
      },
      (error) => {
        console.error('Error saving question:', error);
      }
    );
    this.closeQuestionPopup();
  }

  assignQuestionToQuiz(questionId: number, quizId: number): void {
    this.questionService.assignQuestionToQuiz(questionId, quizId).subscribe(
      (response) => {
        console.log('Question added');
        this.loadQuestions();
      },
      (error) => {
        console.error('Error assigning question to quiz:', error);
        this.loadQuestions();
      }
    );
  }

  // ********************************************************************************************************************************
  // * Option
  newOption: Option = new Option(); // New option to be added
  showOptionPopup: boolean = false;

  openOptionPopup(): void {
    this.showOptionPopup = true;
  }

  closeOptionPopup(): void {
    this.showOptionPopup = false;
  }

  saveOption(currectQuestion: Question): void {
    this.quizService.saveOption(this.newOption).subscribe(
      (savedOption) => {
        this.assignOptionToQuestion(
          savedOption.optionId,
          currectQuestion.questionId
        );
      },
      (error) => {
        console.error('Error saving option:', error);
        // Handle the error appropriately
      }
    );
    this.closeOptionPopup(); // Close the option popup after saving
  }

  assignOptionToQuestion(optionId: number, questionId: number): void {
    this.quizService.assignOptionToQuestion(optionId, questionId).subscribe(
      (response) => {
        console.log('Option assigned to question successfully:', response);
        this.loadQuestions();
      },
      (error) => {
        console.error('Error assigning option to question:', error);
        this.loadQuestions();
      }
    );
  }

  // ********************************************************************************************************************************
  // * Media
  newMedia: Media = new Media();


  openMediaAlert(question: Question): void {
    const mediaLink = window.prompt('Enter the media link:');
    if (mediaLink !== null) {
      // User entered a value
      this.saveMedia(question, mediaLink.trim());
    } else {
      // User clicked Cancel
      console.log('User cancelled the media input.');
    }
  }

  saveMedia(question: Question, mediaLink: string): void {
    const newMedia: Media = {
      mediaId: 0, // Assuming this will be generated by the server
      mediaLink: mediaLink,
    };

    this.quizService.saveMedia(newMedia).subscribe(
      (savedMedia) => {
        console.log('Media saved successfully:', savedMedia);
        this.assignMediaToQuestion(savedMedia.mediaId, question.questionId);
      },
      (error) => {
        console.error('Error saving media:', error);
        // Handle the error appropriately
      }
    );
  }

  assignMediaToQuestion(mediaId: number, questionId: number): void {
    this.quizService.assignMediaToQuestion(mediaId, questionId).subscribe(
      (response) => {
        console.log('Media assigned to question successfully:', response);
        this.loadQuestions();
      },
      (error) => {
        console.error('Error assigning media to question:', error);
        this.loadQuestions();
      }
    );
  }

}
