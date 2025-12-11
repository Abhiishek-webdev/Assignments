package com.abhishek.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.assignment.model.Enrollment;
import com.abhishek.assignment.model.LessonCompletion;
import com.abhishek.assignment.repository.EnrollmentRepository;
import com.abhishek.assignment.repository.LessonCompletionRepository;

@Service
public class LearningService {

    @Autowired
    private EnrollmentRepository enrollmentRepo;
    
    @Autowired
    private LessonCompletionRepository completionRepo;

    // 1. Enroll User
    public String enrollUser(Long courseId, Long userId) { // In real app, userId comes from Security Context
        if (enrollmentRepo.existsByUserIdAndCourseId(userId, courseId)) {
            return "User already enrolled"; // Rule: Enroll only once 
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(userId);
        enrollment.setCourseId(courseId);
        enrollmentRepo.save(enrollment);
        return "Enrolled successfully";
    }

    // 2. Complete Lesson
    public String completeLesson(Long courseId, Long lessonId, Long userId) {
        // Rule: Idempotency (Check if already exists before saving) 
        if (completionRepo.existsByUserIdAndCourseIdAndLessonId(userId, courseId, lessonId)) {
            return "Lesson already completed"; // Return success without duplicating
        }
        LessonCompletion completion = new LessonCompletion();
        completion.setUserId(userId);
        completion.setCourseId(courseId);
        completion.setLessonId(lessonId);
        completionRepo.save(completion);
        return "Lesson marked as complete";
    }

    // 3. Get Progress
    public String getProgress(Long userId, Long courseId) {
        // Assuming a course has a fixed number of lessons (e.g., 10) for this simple assignment
        long completedCount = completionRepo.countByUserIdAndCourseId(userId, courseId);
        return "User has completed " + completedCount + " lessons.";
    }
}