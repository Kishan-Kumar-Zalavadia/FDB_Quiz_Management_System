import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { CourseComponent } from './components/course/course/course.component';
import { QuizComponent } from './components/quiz/quiz.component';
import { TakeQuizComponent } from './components/take-quiz/take-quiz.component';
import { AttemptsComponent } from './components/attempts/attempts.component';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { AddQuizComponent } from './components/add-quiz/add-quiz.component';
import { AddQuestionComponent } from './components/add-question/add-question.component';
import { QuizFeedbackComponent } from './components/quiz-feedback/quiz-feedback.component';
import { LandingComponent } from './components/landing/landing.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    FeedbackComponent,
    CourseComponent,
    QuizComponent,
    TakeQuizComponent,
    AttemptsComponent,
    AddCourseComponent,
    AddQuizComponent,
    AddQuestionComponent,
    QuizFeedbackComponent,
    LandingComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatDialogModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
