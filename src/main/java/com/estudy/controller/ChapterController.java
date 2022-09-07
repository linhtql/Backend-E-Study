package com.estudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudy.entities.Chapter;
import com.estudy.entities.ResponseObject;
import com.estudy.form.ChapterForm;
import com.estudy.model.ChapterInfo;
import com.estudy.service.impl.ChapterService;

@RestController
@RequestMapping("/api/v1/chapter")
public class ChapterController {

	@Autowired
	ChapterService chapterService;

	@GetMapping
	public ResponseEntity<ResponseObject> getAllChapter(@RequestParam(required = false) Long id) {
		try {
			if (id != null) {
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObject("ok", "Get all chapter successfully", chapterService.getOneChapter(id)));
			} else {
				List<ChapterInfo> list_chapterInfo = chapterService.getAllChapter();
				if (list_chapterInfo != null) {
					return ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject("ok", "Get all chapter successfully", list_chapterInfo));
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("not_ok", "Get all chapter faild", ""));
	}

	@PostMapping
	public ResponseEntity<ResponseObject> create(@ModelAttribute ChapterForm chapterForm) {

		try {
			ChapterInfo chapterInfo = chapterService.create(chapterForm);
			if (chapterInfo != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("ok", "Create chapter successfully", chapterInfo));
			} else {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("not_ok", "Create chapter faild", ""));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> update(@ModelAttribute ChapterForm chapterForm, @PathVariable Long id) {
		try {
			ChapterInfo chapterIno = chapterService.update(chapterForm, id);
			if (chapterIno != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("ok", "Update chapter successfully", chapterIno));
			} else {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("not_ok", "Update chapter faild", ""));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
		try {
			ChapterInfo chapter = chapterService.delete(id);
			if (chapter != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("ok", "Delete chapter successfully", chapter));
			} else {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("not_ok", "Delete chapter faild", ""));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}
