package com.estudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudy.entities.ResponseObject;
import com.estudy.form.EditUserForm;
import com.estudy.form.LoginForm;
import com.estudy.form.RegisterForm;
import com.estudy.jwt.JwtTokenProvider;
import com.estudy.model.UserInfo;
import com.estudy.service.impl.UserService;
import com.estudy.utils.CustomUserDetails;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/api/v1")
@Api(tags = "users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/auth/signin")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        UserInfo userInfo = userService.getByUserName(loginForm.getUsername());
        System.out.println(loginForm.getUsername());
        userInfo.setToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Login successfully !", userInfo));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseObject> register(@ModelAttribute RegisterForm registerForm) {
        UserInfo userInfo = userService.register(registerForm);
        if (userInfo != null) {

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Register successfully", userInfo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Register failed !", ""));
        }
    }

    @GetMapping("/user/me")
    ResponseEntity<ResponseObject> loadByToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        UserInfo userInfo = userService.getByUserName(username);

        if (userInfo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query user successfully", userInfo));

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Query user fail", ""));
        }
    }

    // get detail user
    @GetMapping("/user/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable long id) {
        UserInfo userInfo = userService.information(id);

        if (userInfo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query user successfully", userInfo));

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Query user fail", ""));
        }

    }

    // edit user
    @PutMapping("/user/{id}")
    ResponseEntity<ResponseObject> updateUser(@ModelAttribute RegisterForm registerForm, @PathVariable long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        UserInfo userInfo = userService.getByUserName(username);

        if (userInfo.getId() != id) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("failed", "Update user failed !", "no permission"));
        }

        UserInfo userInfoUpdate = userService.updateUser(registerForm, id);
        if (userInfoUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("failed", "Update user failed !", ""));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "Update user successfully", userInfoUpdate));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/user/{id}")
    ResponseEntity<ResponseObject> editUser(@ModelAttribute EditUserForm editUserForm, @PathVariable long id) {
        UserInfo userInfo = userService.editUser(editUserForm, id);
        if (userInfo != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Edit user successfully", userInfo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("failed", "Edit user failed !", ""));
        }
    }

}
