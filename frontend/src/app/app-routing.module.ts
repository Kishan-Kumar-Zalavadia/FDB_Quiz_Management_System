import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { CourseComponent } from './components/course/course/course.component';
import { QuizComponent } from './components/quiz/quiz.component';
import { TakeQuizComponent } from './components/take-quiz/take-quiz.component';
import { AttemptsComponent } from './components/attempts/attempts.component';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { AddQuizComponent } from './components/add-quiz/add-quiz.component';

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      { path: 'profile', component: ProfileComponent },
      { path: 'feedback', component: FeedbackComponent },
      { path: 'course', component: CourseComponent },
      { path: 'addCourse', component: AddCourseComponent },
      { path: 'quizzes', component: QuizComponent },
      { path: 'attempts', component: AttemptsComponent },
      { path: 'addQuiz/:courseId', component: AddQuizComponent },
    ],
  },
  { path: 'quizzes/:quizId', component: TakeQuizComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
