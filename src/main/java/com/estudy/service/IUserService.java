package com.estudy.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.estudy.entities.User;
import com.estudy.form.EditUserForm;
import com.estudy.form.RegisterForm;
import com.estudy.model.UserInfo;

public interface IUserService {

    UserDetails loadUserById(long id);

    UserInfo getByUserName(String username);

    UserInfo register(RegisterForm registerForm);

    UserInfo information(long id);

    UserInfo updateUser(RegisterForm registerForm, Long id);

    UserInfo editUser(EditUserForm editUserForm, Long id);

    UserInfo convertToUserInfo(User user);

}
