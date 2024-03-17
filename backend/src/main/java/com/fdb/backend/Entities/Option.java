package com.fdb.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;
    private String optionName;
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
//    @JsonIgnore
    @JsonIgnoreProperties("options")
    private Question question;

//    @ManyToMany(mappedBy = "options")
//    private List<QuizAttempt> quizAttempts;
}
