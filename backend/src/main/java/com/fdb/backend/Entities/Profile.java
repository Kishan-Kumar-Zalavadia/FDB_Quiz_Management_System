package com.fdb.backend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String DOB;
    private int age;



}

