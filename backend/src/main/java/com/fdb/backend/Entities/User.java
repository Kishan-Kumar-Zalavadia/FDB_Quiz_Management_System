package com.fdb.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
//    @JoinColumn(name = "profileID")
    private Profile profile;
}
