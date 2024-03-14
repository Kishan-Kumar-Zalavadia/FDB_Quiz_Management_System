package com.fdb.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Profile {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int profileID;
//    private String firstName;
//    private String lastName;
//    private long phoneNumber;
//    private String photoURL;
//    private String streetNumber;
//    private String streetName;
//    private String aptNumber;
//    private String city;
//    private String state;
//    private String zip;
//    private String country;
//    private String DOB;
//    private int age;
//
//    // Bidirectional mapping
//    // To use this we need to use @JoinColumn in User.
//    @OneToOne(mappedBy = "profile")
//    private User user;
//
//}
//

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileID;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String photoURL;
    private String streetNumber;
    private String streetName;
    private String aptNumber;
    private String city;
    private String state;
    private String zip;
    private String country;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private int age;

    // Many-1 (Profile - Department)
    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;
}

