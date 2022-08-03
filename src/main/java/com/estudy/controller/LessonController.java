package com.estudy.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.estudy.entities.ResponseObject;
import com.estudy.form.CategoryForm;
import com.estudy.form.LessonForm;
import com.estudy.model.CategoryInfo;
import com.estudy.model.LessonInfor;
import com.estudy.service.impl.LessonService;

@RestController
@RequestMapping(path = "/api/v1/lession")
public class LessonController {

	@Autowired
	LessonService lessonService;

	@GetMapping("/chapter/{id}")
	public ResponseEntity<ResponseObject> getAllLessonWithChapterId(@PathVariable Long id) {
		List<LessonInfor> list_lessonInfo = lessonService.getAllLessonByIdChapter(id);

		if (list_lessonInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Get all lesson by chapter id successfully", list_lessonInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Get all lesson by chapter id failed !", ""));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> getLessonById(@PathVariable Long id) {
		LessonInfor lessonInfo = lessonService.getLessonById(id);

		if (lessonInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Get lesson by id successfully", lessonInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Get lesson by id failed !", ""));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
		LessonInfor lessonInfo = lessonService.delete(id);

		if (lessonInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Get lesson by id successfully", lessonInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Get lesson by id failed !", ""));
		}
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ResponseObject> create(@ModelAttribute LessonForm lessonForm) {
		LessonInfor lessonInfo = lessonService.create(lessonForm);

		if (lessonInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Create lesson successfully", lessonInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Create lesson failed !", ""));
		}
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> update(@ModelAttribute LessonForm lessonForm, @PathVariable Long id) {
		LessonInfor lessonInfo = lessonService.update(lessonForm, id);

		if (lessonInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Update lesson successfully", lessonInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Update lesson failed !", ""));
		}
	}
}
