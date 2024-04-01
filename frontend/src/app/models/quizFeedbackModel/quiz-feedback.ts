import { Quiz } from "../quizModel/quiz";
import { User } from "../userModel/user";

export class QuizFeedback {
    id!: number;
    text!: string;
    date!: Date;
    time!: string;
    user!: User;
    quiz!: Quiz;
}
