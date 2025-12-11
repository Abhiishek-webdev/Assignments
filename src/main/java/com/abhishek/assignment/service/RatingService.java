package com.abhishek.assignment.service;

import com.abhishek.assignment.model.Rating;
import com.abhishek.assignment.repository.EnrollmentRepository;
import com.abhishek.assignment.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
    private EnrollmentRepository enrollmentRepo; // Reusing from Assignment 1

    public String addRating(Long courseId, Long userId, Integer score, String comment) {
        // Rule 1: Only enrolled users can rate
        boolean isEnrolled = enrollmentRepo.existsByUserIdAndCourseId(userId, courseId);
        if (!isEnrolled) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must be enrolled to rate this course.");
        }

        // Rule 2: One rating per user per course
        if (ratingRepo.existsByUserIdAndCourseId(userId, courseId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User has already rated this course.");
        }

        Rating rating = new Rating();
        rating.setUserId(userId);
        rating.setCourseId(courseId);
        rating.setScore(score);
        rating.setComment(comment);
        ratingRepo.save(rating);

        return "Rating submitted successfully.";
    }

    public List<Rating> getCourseRatings(Long courseId) {
        return ratingRepo.findByCourseId(courseId);
    }
}
