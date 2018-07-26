package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/tasks")
    public String getTasks(){
        return "These are the tasks";
    }

    @PostMapping("/tasks")
    public String postTask(){
        return "You just posted a task";
    }

    @GetMapping("/math/pi")
    public String getPi(){
        return "3.141592653589793";
    }
}