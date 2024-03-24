import { QuizAttempt } from "../attemptModel/attempt";
import { Course } from "../courseModel/course";
import { Question } from "../questionModel/question";

export class Quiz {
  quizId!: number;
  quizTitle!: string;
  quizDuration!: number;
  quizNumberOfAttempts!: number;
  accessCode!: string;
  createdDate!: Date;
  endDate!: Date;
  endTime!: string;
  instruction!: string;
  questions!: Question[];
  attempt!: QuizAttempt[];
  course!: Course;
}


