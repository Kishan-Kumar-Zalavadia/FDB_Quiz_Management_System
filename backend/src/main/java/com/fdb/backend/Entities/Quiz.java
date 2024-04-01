package com.fdb.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;

    private String quizTitle;
    private int quizDuration;
    private int quizNumberOfAttempts;
    private String accessCode;
    private LocalDate createdDate;
    private LocalDate endDate;
    private LocalTime endTime;
    private String instruction;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<QuizAttempt> quizAttempt;

    @ManyToOne
    private Course course;

//    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
//    private List<QuizFeedback> quizFeedbacks;
}