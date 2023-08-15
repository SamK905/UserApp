package com.syam.userapp.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<String> users = new ArrayList<>();
    private final RestTemplate restTemplate;

    public UserController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name) {
        users.add(name);
        restTemplate.postForEntity("http://localhost:8081/notify", name, String.class);
        return "User registered successfully!";
    }

    @GetMapping
    public List<String> listUsers() {
        return users;
    }
}
