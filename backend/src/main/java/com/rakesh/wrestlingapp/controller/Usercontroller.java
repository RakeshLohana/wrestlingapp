package com.rakesh.wrestlingapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Usercontroller {



    @GetMapping
    public String getUser(){
        return "users is ghere";
    }

}
