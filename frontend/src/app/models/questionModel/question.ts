import { Media } from "../mediaModel/media";
import { Option } from "../optionModel/option";

export class Question {
  questionId!: number;
  questionText!: string;
  questionMarks!: number;
  options!: Option[];
  mediaList!: Media[];
}
