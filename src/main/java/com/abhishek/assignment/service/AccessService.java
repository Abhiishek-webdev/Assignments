package com.abhishek.assignment.service;

import com.abhishek.assignment.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class AccessService {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    public List<String> getLessons(Long courseId, Long userId) {
        // Rule: Check Enrollment
        boolean isEnrolled = enrollmentRepo.existsByUserIdAndCourseId(userId, courseId);
        
        if (!isEnrolled) {
            // This throws a 403 Forbidden Error exactly as requested
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied: You are not enrolled in this course.");
        }

        // Mock return of lessons
        return Arrays.asList("Lesson 1: Intro", "Lesson 2: Setup", "Lesson 3: Advanced Topics");
    }
}
