package com.example.demologin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demologin.domain.User;
import com.example.demologin.repository.Userrepository;



@Controller
public class Usercontroller {
    @Autowired
    private Userrepository repo;
@GetMapping("/")
public String homepage()
{
    return "home.html";
}
@PostMapping("/login")
@ResponseBody
public String loginpage(@RequestParam String username, @RequestParam String password) {
    User user = repo.findByUsernameAndPassword(username, password);
    if (user != null) {
        return "Login successful! Welcome, " + user.getUsername() + "!";
    } else {
        return "Invalid username or password. Please try again.";
    }
}
}
