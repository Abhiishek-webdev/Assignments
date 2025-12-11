package com.abhishek.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.assignment.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}