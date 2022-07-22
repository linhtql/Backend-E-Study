package com.estudy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

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
				.body(new ResponseObject("ok", "Get all category succesfully", categoryService.getAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> getOneCategory(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "Get one category by id succesfully", categoryService.getOneCategory(id)));
	}

	@PostMapping
	public ResponseEntity<ResponseObject> create(@ModelAttribute CategoryForm categoryForm) {
		CategoryInfo catogryInfo = categoryService.create(categoryForm);

		if (catogryInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Create category successfully", catogryInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Create category failed !", "Category already exists"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> update(@ModelAttribute CategoryForm categoryForm, @PathVariable Long id) {
		CategoryInfo catogryInfo = categoryService.update(categoryForm, id);

		if (catogryInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Update category successfully", catogryInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Update category failed !", "Category already exists"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
		CategoryInfo catogryInfo = categoryService.delete(id);

		if (catogryInfo != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Delete category successfully", catogryInfo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Delete category failed !", "Category already exists"));
		}
	}
}
