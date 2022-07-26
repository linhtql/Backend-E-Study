package com.estudy.controller;

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
import com.estudy.jwt.JwtAuthenticationFilter;
import com.estudy.jwt.JwtTokenProvider;
import com.estudy.model.CategoryInfo;
import com.estudy.service.impl.CategoryService;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	JwtAuthenticationFilter jwtAuthenFilter;

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity<ResponseObject> getAllCategory() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Get all category successfully", categoryService.getAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> getOneCategory(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "Get one category by id successfully", categoryService.getOneCategory(id)));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ResponseObject> create(@ModelAttribute CategoryForm categoryForm) {
		CategoryInfo categoryInfo = categoryService.create(categoryForm);

		if (categoryInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Create category successfully", categoryInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Create category failed !", "Category already exists"));
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> update(@ModelAttribute CategoryForm categoryForm, @PathVariable Long id) {
		CategoryInfo categoryInfo = categoryService.update(categoryForm, id);

		if (categoryInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Update category successfully", categoryInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Update category failed !", "Category already exists"));
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
		CategoryInfo categoryInfo = categoryService.delete(id);

		if (categoryInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Delete category successfully", categoryInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Delete category failed !", "Category already exists"));
		}
	}
}
