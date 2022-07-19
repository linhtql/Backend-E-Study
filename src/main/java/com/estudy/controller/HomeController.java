package com.estudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/abc")
public class HomeController {

    @GetMapping("/index")
    public String index(){
        return "only logged";
    }
}
