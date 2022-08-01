package com.estudy.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estudy.entities.Comment;

@Repository
public interface CommentReponsitory extends JpaRepository<Comment, Long> {
	Comment findOneById(Long id);

	List<Comment> findAllByCourseId(Long id);

	Integer countByCourseId(Long id);

//
	@Query(nativeQuery = true, value = "SELECT * FROM comments c WHERE c.course_id=:courseId LIMIT :start, :limit")
	List<Comment> findAllByCourseIdParamsNative(Long courseId, Integer start, Integer limit);

}
