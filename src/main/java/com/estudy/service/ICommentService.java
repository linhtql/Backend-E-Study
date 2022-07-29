package com.estudy.service;

import javax.servlet.http.HttpServletRequest;

import com.estudy.form.CommentForm;
import com.estudy.model.CommentInfo;
import com.estudy.model.PaginationCommentInfo;

public interface ICommentService {
	CommentInfo create(CommentForm commentForm, HttpServletRequest req);

	CommentInfo update(CommentForm commentForm, Long id);

	CommentInfo delete(Long id);

	PaginationCommentInfo getAllOrPagination(Boolean p, Long courseId);

	PaginationCommentInfo getAllOrPagination(Boolean p, Long courseId, Integer current_page, Integer limit,
			String sort);
}
