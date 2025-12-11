package com.abhishek.assignment.repository;

import com.abhishek.assignment.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    // Helper to find ratings for a specific course
    List<Rating> findByCourseId(Long courseId);
    
    // Helper to check if a user has already rated a course
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
