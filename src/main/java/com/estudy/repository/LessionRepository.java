package com.estudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudy.entities.Lesson;

@Repository
public interface LessionRepository extends JpaRepository<Lesson, Long> {
	Lesson findOneByName(String name);

	Lesson findOneById(Long id);

	List<Lesson> findByChapterId(Long chapter_id);
}
