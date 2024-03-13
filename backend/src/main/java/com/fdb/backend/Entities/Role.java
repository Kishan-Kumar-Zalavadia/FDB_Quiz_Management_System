package com.fdb.backend.Entities;

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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleID;
    private String roleType;

    // 1-n (User - Role)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleID", referencedColumnName = "roleID")
    private List<User> user;
}
