package com.fdb.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "attempts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizAttemptId;

    private int attemptNumber;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate attemptDate;

//     Define relationships
    @ManyToOne
    @JsonIgnore
//    @JoinColumn(name = "user_id", nullable = false)
    private User user;
//
    @ManyToOne
    @JsonIgnore
//    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

//    @ManyToMany
//    @JoinTable(
//            name = "quiz_attempt_option",
//            joinColumns = @JoinColumn(name = "quiz_attempt_id"),
//            inverseJoinColumns = @JoinColumn(name = "option_id")
//    )
//    private List<Option> selectedOptions;

}
