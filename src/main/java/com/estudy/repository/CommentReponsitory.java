package com.estudy.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estudy.entities.Comment;

@Repository
@Transactional
public interface CommentReponsitory extends JpaRepository<Comment, Long> {
	Comment findOneById(Long id);

	List<Comment> findAllByCourseId(Long id);

	Integer countByCourseId(Long id);

//
	@Query("SELECT c FROM Comment c")
	List<Comment> findAllByCourseIdAndLimit(Long courseId, Integer start, Integer limit);

}
