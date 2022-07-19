package com.estudy.service;

import com.estudy.form.RegisterForm;
import com.estudy.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    UserDetails loadUserById(long id);
    UserInfo getByUserName(String username);
    UserInfo register(RegisterForm registerForm);
}
