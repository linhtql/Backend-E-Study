package com.estudy.controller;

import com.estudy.entities.ResponseObject;
import com.estudy.form.LoginForm;
import com.estudy.form.RegisterForm;
import com.estudy.jwt.JwtTokenProvider;
import com.estudy.model.UserInfo;
import com.estudy.service.impl.UserService;
import com.estudy.utils.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
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

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Register successfully", userInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Register failed !", ""));
		}
	}

	// get detail user
	@GetMapping("/{id}")
	ResponseEntity<ResponseObject> findById(@PathVariable long id) {
		UserInfo userInfo = userService.information(id);

		if (userInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Query user successfully", userInfo));

		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Query user fail", ""));
		}

	}

}
