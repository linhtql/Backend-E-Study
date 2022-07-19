package com.estudy.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    UserDetails loadUserById(long id);
}
