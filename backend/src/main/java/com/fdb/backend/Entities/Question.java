package com.fdb.backend.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String questionText;
    private int questionMarks;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
