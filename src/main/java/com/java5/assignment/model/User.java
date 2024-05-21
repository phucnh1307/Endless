package com.java5.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean status;
}
