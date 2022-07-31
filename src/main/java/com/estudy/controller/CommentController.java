package com.estudy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudy.entities.Comment;
import com.estudy.entities.ResponseObject;
import com.estudy.form.CommentForm;
import com.estudy.model.CommentInfo;
import com.estudy.service.impl.CommentService;

@RestController
@RequestMapping(path = "/api/v1/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{courseId}")
	public ResponseEntity<ResponseObject> getAllOrPagination(@RequestParam(required = true) Boolean p,
			@RequestParam(required = false, defaultValue = "1") Integer current_page,
			@RequestParam(required = false, defaultValue = "10") Integer limit,
			@RequestParam(required = false, defaultValue = "desc") String sort, @PathVariable Long courseId) {
		if (p) {

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("true", "Da vao Comment",
					commentService.getAllOrPagination(p, courseId, current_page, limit, sort)));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("true", "Da vao Comment", commentService.getAllOrPagination(p, courseId)));
		}

	}

	@PostMapping
	public ResponseEntity<ResponseObject> create(@ModelAttribute CommentForm commentForm, HttpServletRequest req) {

		CommentInfo commentInfo = commentService.create(commentForm, req);

		if (commentInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("true", "Create comment sucssesfully", commentInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Create comment failed !", ""));
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> update(@ModelAttribute CommentForm commentForm, @PathVariable Long id) {
		CommentInfo commentInfo = commentService.update(commentForm, id);

		if (commentInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("true", "Update comment sucssesfully", commentInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Update comment failed !", ""));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
		CommentInfo commentInfo = commentService.delete(id);

		if (commentInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("true", "Delete comment sucssesfully", commentInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Delete comment failed !", ""));
		}
	}
}
