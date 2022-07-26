package com.estudy.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.estudy.entities.Comment;

public interface CommentReponsitory extends JpaRepository<Comment, Long> {
	Comment findOneById(Long id);

//	List<Comment> findAllByCourseId(Long id);

	Integer countByCourseId(Long id);

	@Query("SELECT c FROM Comment c ORDER BY c.id DESC")
	List<Comment> findAllByCourseId(Pageable pageable);
}
