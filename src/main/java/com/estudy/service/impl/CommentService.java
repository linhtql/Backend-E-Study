package com.estudy.service.impl;

import java.net.http.HttpRequest;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.estudy.convert.CommentConvert;
import com.estudy.entities.Comment;
import com.estudy.entities.User;
import com.estudy.form.CommentForm;
import com.estudy.jwt.JwtAuthenticationFilter;
import com.estudy.jwt.JwtTokenProvider;
import com.estudy.model.CommentInfo;
import com.estudy.model.PaginationCommentInfo;
import com.estudy.repository.CategoryRepository;
import com.estudy.repository.CommentReponsitory;
import com.estudy.repository.UserRepository;
import com.estudy.service.ICommentService;

@Service
public class CommentService implements ICommentService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommentReponsitory commentRepository;

	@Autowired
	CommentConvert commentConvert;

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Override
	public CommentInfo create(CommentForm commentForm, HttpServletRequest req) {

		Long id = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getToken(req));

		User data_user = userRepository.findOneById(id);
		if (data_user != null) {

			commentForm.setUserId(id);

			if (commentForm.getContent().trim().isEmpty()) {
				return null;
			} else {
				if (commentForm.getStar() != null && commentForm.getStar() >= 0 && commentForm.getStar() <= 5) {
					commentForm.setStar(commentForm.getStar());
				} else {
					commentForm.setStar(0.0);
				}

				Comment comment1 = commentConvert.toEntity(commentForm);
				comment1.setCreatedDate(new Date());

				Comment comment = commentRepository.save(comment1);

				CommentInfo commentInfo = commentConvert.toModel(comment);

				return commentInfo;
			}
		} else {
			return null;
		}
	}

	@Override
	public CommentInfo update(CommentForm commentForm, Long id) {
		Comment commentOld = commentRepository.findOneById(id);

		if (commentOld != null) {
			Comment comment1 = commentConvert.toEntity(commentOld, commentForm);

			Comment comment = commentRepository.save(comment1);

			CommentInfo commentInfo = commentConvert.toModel(comment);
			return commentInfo;
		} else {
			return null;
		}
	}

	@Override
	public CommentInfo delete(Long id) {
		Comment oldComment = commentRepository.findOneById(id);

		if (oldComment != null) {
			commentRepository.deleteById(id);
			return commentConvert.toModel(oldComment);
		} else {
			return null;
		}

	}

	@Override
	public PaginationCommentInfo getAllOrPagination(Boolean p, Long courseId) {
		if (!p) {

			List<Comment> listComment = commentRepository.findAllByCourseId(courseId);
			if (listComment != null) {

				PaginationCommentInfo paginationComment = new PaginationCommentInfo();
				paginationComment.setContent(commentConvert.toListModel(listComment));
				paginationComment.setTotalPages(1);
				paginationComment.setTotalElements(listComment.size());
				paginationComment.setCurrentPage(1);

				return paginationComment;

			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	@Override
	public PaginationCommentInfo getAllOrPagination(Boolean p, Long courseId, Integer current_page, Integer limit,
			String sort) {
		try {
			if (p) {

				PaginationCommentInfo paginationComment = new PaginationCommentInfo();
				Integer total_record = commentRepository.countByCourseId(courseId);
				Integer totalPage = (int) Math.ceil(((double) total_record) / limit);

				if (current_page > totalPage) {
					current_page = totalPage;
				} else if (current_page < 1) {
					current_page = 1;
				}

				Integer start = (current_page - 1) * limit;

				System.out.println(courseId + ", " + start + ", " + limit + ", " + sort.toUpperCase());

				Sort sort_1 = sort.toUpperCase() == "DESC" ? Sort.by(Sort.Direction.DESC, "createdDate")
						: Sort.by(Sort.Direction.ASC, "createdDate");

				List<Comment> resuft = commentRepository.findAllByCourseIdParamsNative(courseId, start, limit);

				PaginationCommentInfo paginationComment1 = new PaginationCommentInfo();
				paginationComment1.setContent(commentConvert.toListModel(resuft));
				paginationComment1.setTotalPages(totalPage);
				paginationComment1.setTotalElements(resuft.size());
				paginationComment1.setCurrentPage(current_page);

				return paginationComment1;

			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
