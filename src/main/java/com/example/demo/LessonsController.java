package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }


    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{userId}")
    public Optional<Lesson> findById(@PathVariable long userId) {
        return this.repository.findById(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteById(@PathVariable long userId) {
        try {
            this.repository.deleteById(userId);
            return  String.format("Entry with Id: %d,  was successfully removed", userId);
        }
        catch (IllegalArgumentException e) {
            return String.format(e.getMessage());
        }
    }
}
