package com.estudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudy.model.UserInfo;
import com.estudy.service.impl.UserService;

@RestController
@RequestMapping("/mail")
public class MailController {
	@Autowired
    private UserService userService;

    @PostMapping(value = "create")
    public ResponseEntity<Boolean> create(
            @RequestBody long id
    ) {
        return ResponseEntity.ok(userService.createMail(id));
    }
}
