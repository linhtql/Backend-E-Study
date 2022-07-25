package com.estudy.service;

import java.util.List;

import com.estudy.form.CategoryForm;
import com.estudy.model.CategoryInfo;

public interface ICategoryService {
	CategoryInfo create(CategoryForm categoryForm);

	CategoryInfo update(CategoryForm categoryForm, Long id);

	CategoryInfo delete(Long id);

	List<CategoryInfo> getAll();

	CategoryInfo getOneCategory(Long id);
}
