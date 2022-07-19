package com.estudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Date birthOfDate;
    private String phone;
    private String email;
    private String address;
    private String avatar;
    private String token;
}
