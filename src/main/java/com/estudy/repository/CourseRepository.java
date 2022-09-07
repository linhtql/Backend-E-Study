package com.estudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudy.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findOneById(Long id);
}
