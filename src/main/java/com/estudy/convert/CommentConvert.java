package com.estudy.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudy.entities.Comment;
import com.estudy.form.CommentForm;
import com.estudy.model.CommentInfo;
import com.estudy.service.IUserService;

@Component
public class CommentConvert {
	@Autowired
	IUserService userService;

	public Comment toEntity(CommentForm commentForm) {
		Comment commentEntity = new Comment();

		commentEntity.setContent(commentForm.getContent());
		commentEntity.setUserId(commentForm.getUserId());
		commentEntity.setCourseId(commentForm.getCourseId());
		commentEntity.setStar(commentForm.getStar());

		return commentEntity;
	}

	public CommentInfo toModel(Comment comment) {

		CommentInfo commentInfo1 = new CommentInfo();

		commentInfo1.setId(comment.getId());
		commentInfo1.setContent(comment.getContent());
		commentInfo1.setStart(comment.getStar());
		commentInfo1.setCreateAt(comment.getCreatedDate());
		commentInfo1.setCourseId(comment.getCourseId());
		commentInfo1.setUserId(comment.getUserId());
		commentInfo1.setUser(comment.getUser() != null ? userService.convertToUserInfo(comment.getUser()) : null);

		return commentInfo1;
	}

	public Comment toEntity(Comment oldComment, CommentForm commentForm) {

		if (commentForm.getContent().trim().isEmpty()) {
			oldComment.setContent(oldComment.getContent());
		} else {
			oldComment.setContent(commentForm.getContent());
		}

		if (commentForm.getStar() != null && commentForm.getStar() >= 0 && commentForm.getStar() <= 5) {
			oldComment.setStar(commentForm.getStar());
		} else {
			oldComment.setStar(0.0);
		}

		oldComment.setCourseId(oldComment.getCourseId());
		oldComment.setUserId(oldComment.getUserId());

		return oldComment;
	}

	public List<CommentInfo> toListModel(List<Comment> list_comment) {
		List<CommentInfo> list = new ArrayList<CommentInfo>();

		for (Comment comment : list_comment) {
			list.add(toModel(comment));
		}

		return list;
	}
}
