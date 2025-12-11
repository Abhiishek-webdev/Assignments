package com.abhishek.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.assignment.service.LearningService;

@RestController
@RequestMapping
public class LearningController {

    @Autowired
    private LearningService learningService;

    // POST /courses/{courseId}/enroll
    @PostMapping("/courses/{courseId}/enroll")
    public ResponseEntity<String> enroll(@PathVariable Long courseId, @RequestBody Long userId) {
        // Note: Usually userId is in the body or header. I'm assuming body for simplicity.
        return ResponseEntity.ok(learningService.enrollUser(courseId, userId));
    }

    // POST /courses/{courseId}/lessons/{lessonId}/complete
    @PostMapping("/courses/{courseId}/lessons/{lessonId}/complete")
    public ResponseEntity<String> completeLesson(
            @PathVariable Long courseId, 
            @PathVariable Long lessonId,
            @RequestBody Long userId) {
        return ResponseEntity.ok(learningService.completeLesson(courseId, lessonId, userId));
    }

    // GET /users/{userId}/courses/{courseId}/progress
    @GetMapping("/users/{userId}/courses/{courseId}/progress")
    public ResponseEntity<String> getProgress(@PathVariable Long userId, @PathVariable Long courseId) {
        return ResponseEntity.ok(learningService.getProgress(userId, courseId));
    }
}

