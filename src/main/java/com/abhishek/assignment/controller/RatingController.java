package com.abhishek.assignment.controller;

import com.abhishek.assignment.model.Rating;
import com.abhishek.assignment.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // POST /courses/{courseId}/rating
    @PostMapping("/{courseId}/rating")
    public ResponseEntity<String> addRating(
            @PathVariable Long courseId,
            @RequestBody RatingRequest request) { // Create a simple DTO or use Map
        
        return ResponseEntity.ok(ratingService.addRating(
                courseId, 
                request.getUserId(), 
                request.getScore(), 
                request.getComment()));
    }

    // GET /courses/{courseId}/rating
    @GetMapping("/{courseId}/rating")
    public ResponseEntity<List<Rating>> getRatings(@PathVariable Long courseId) {
        return ResponseEntity.ok(ratingService.getCourseRatings(courseId));
    }
    
    // Simple DTO class for the request body
    @lombok.Data
    static class RatingRequest {
        private Long userId;
        private Integer score;
        private String comment;
    }
}