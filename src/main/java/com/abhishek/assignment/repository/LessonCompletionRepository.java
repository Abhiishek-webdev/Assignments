package com.abhishek.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.assignment.model.LessonCompletion;

public interface LessonCompletionRepository extends JpaRepository<LessonCompletion, Long> {
    boolean existsByUserIdAndCourseIdAndLessonId(Long userId, Long courseId, Long lessonId);
    long countByUserIdAndCourseId(Long userId, Long courseId);
}
