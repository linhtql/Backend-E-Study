package com.estudy.form;

import com.estudy.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private Date birthOfDate;
    private String phone;
    private String email;
    private String address;
    private MultipartFile avatar;
    private boolean active;
    private Long roleId;

}
