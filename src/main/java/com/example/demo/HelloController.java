package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

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

    @GetMapping("/math/calculate")
    public String calculate(WebRequest webRequest) {
        Map<String, String[]> params = webRequest.getParameterMap();
        MathService mathService = new MathService();
        return mathService.calculate(params);
    }

    @PostMapping("/math/sum")
    public String sum(@RequestParam MultiValueMap<String, String> queryString){
        MathService mathService = new MathService();
        return mathService.sum(queryString.get("n"));
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String calculateVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
        int volume = length * width * height;
        return String.format("The volume of a %sx%sx%s rectangle is %d", length, width, height, volume);
    }
}