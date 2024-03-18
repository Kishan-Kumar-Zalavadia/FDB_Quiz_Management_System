import { Time } from "@angular/common";
import { User } from "../userModel/user";
import { Quiz } from "../quizModel/quiz";
import { Option } from '../optionModel/option';


export class QuizAttempt {
  quizAttemptId!: number;
  attemptNumber!: number;
  startTime?: Time;
  endTime?: Time;
  attemptDate?: Date;


  user!: User;
  quiz!: Quiz;
  selectedOptions!: Option[];
}
