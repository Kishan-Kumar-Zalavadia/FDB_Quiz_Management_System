package com.fdb.backend.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    private User user;

    @ManyToOne
    private Quiz quiz;
}
