package com.fdb.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    @Column(unique = true)
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<User> users;

//    @ManyToMany(mappedBy = "courses")
//    private List<Department> departments;

    @ManyToMany
    @JoinTable(
            name = "department_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departments;

    @ManyToOne
    private User professor;
}