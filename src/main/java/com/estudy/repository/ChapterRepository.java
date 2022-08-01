package com.estudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estudy.entities.Category;
import com.estudy.entities.Chapter;

import io.jsonwebtoken.JwtParser;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
	Chapter findOneByName(String name);

	Chapter findOneById(Long id);

//	@Query(value = "", nativeQuery = true)

}
