package com.estudy.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSocialForm {
    private String email;
    private String picture;
    private String id;
    private String firstName;
    private String lastName;
}
