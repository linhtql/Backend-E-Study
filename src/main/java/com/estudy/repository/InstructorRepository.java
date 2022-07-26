package com.estudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudy.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor findOneById(Long id);

    Instructor findFirstByUserId(Long id);
}
