package com.estudy.service;

import com.estudy.entities.User;
import com.estudy.form.EditUserForm;
import com.estudy.form.RegisterForm;
import com.estudy.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    UserDetails loadUserById(long id);
    UserInfo getByUserName(String username);
    UserInfo register(RegisterForm registerForm);
    UserInfo information(long id);
    UserInfo updateUser(RegisterForm registerForm, Long id);
    UserInfo editUser(EditUserForm editUserForm, Long id);
}
