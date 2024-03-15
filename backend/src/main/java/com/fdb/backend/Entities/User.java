package com.fdb.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int userID;
//    private String emailID;
//    private String userName;
//    private String password;
//
//    // 1-1 (User - Profile)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "profileID")
//    private Profile profile;
//
//    // Many-1 (User - Role)
//    @ManyToOne
//    @JoinColumn(name = "roleID")
//    private Role role;
//
//    // 1-Many (User - Feedback)
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userID", referencedColumnName = "userID")
//    private List<Feedback> feedback;
//}

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String emailID;
    private String userName;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profileID")
    private Profile profile;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleID")
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}