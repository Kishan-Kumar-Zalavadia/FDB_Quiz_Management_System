package com.fdb.backend.Entities;


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
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
