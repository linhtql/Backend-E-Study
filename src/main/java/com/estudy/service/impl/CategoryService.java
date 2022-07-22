package com.estudy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudy.entities.Category;
import com.estudy.form.CategoryForm;
import com.estudy.model.CategoryInfo;
import com.estudy.repository.CategoryRepository;
import com.estudy.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	StorageService storageService;

	@Override
	public CategoryInfo create(CategoryForm categoryForm) {
		CategoryInfo categoryInfo = new CategoryInfo();
		Category category1 = categoryRepository.findOneByName(categoryForm.getName().trim());

		if (category1 == null) {
			category1 = new Category();
			category1.setName(categoryForm.getName());
			category1.setDescription(categoryForm.getDescription());
			String avatar;
			if (categoryForm.getAvatar().isEmpty()) {
				avatar = "https://res.cloudinary.com/dxultkptn/image/upload/v1658150890/default_nbaeby.jpg";
			} else {
				avatar = storageService.storageFile(categoryForm.getAvatar());
			}

			category1.setAvatar(avatar);
			category1.setCreatedDate(new Date());

			Category category = categoryRepository.save(category1);

			categoryInfo.setId(category.getId());
			categoryInfo.setName(category.getName());
			categoryInfo.setAvatar(category.getAvatar());
			categoryInfo.setDescription(category.getDescription());

			return categoryInfo;
		} else {
			return null;
		}
	}

	@Override
	public CategoryInfo update(CategoryForm categoryForm, Long id) {
		CategoryInfo categoryInfo = new CategoryInfo();

		Category oldCateogry = categoryRepository.findOneById(id);
		if (oldCateogry != null) {
			oldCateogry.setName(categoryForm.getName());
			oldCateogry.setDescription(categoryForm.getDescription());
			String avatar;
			if (categoryForm.getAvatar().isEmpty()) {
				avatar = oldCateogry.getAvatar();
			} else {
				avatar = storageService.storageFile(categoryForm.getAvatar());

			}
			oldCateogry.setAvatar(avatar);
			oldCateogry.setCreatedDate(new Date());

			Category category = categoryRepository.save(oldCateogry);

			categoryInfo.setId(category.getId());
			categoryInfo.setName(category.getName());
			categoryInfo.setAvatar(category.getAvatar());
			categoryInfo.setDescription(category.getDescription());

			return categoryInfo;
		} else {
			return create(categoryForm);
		}

	}

	@Override
	public CategoryInfo delete(Long id) {
		Category oldCateogry = categoryRepository.findOneById(id);
		CategoryInfo categoryInfo = new CategoryInfo();

		if (oldCateogry != null) {
			categoryRepository.deleteById(id);

			categoryInfo.setId(oldCateogry.getId());
			categoryInfo.setName(oldCateogry.getName());
			categoryInfo.setAvatar(oldCateogry.getAvatar());
			categoryInfo.setDescription(oldCateogry.getDescription());

			return categoryInfo;
		} else {
			return null;
		}
	}

	@Override
	public List<CategoryInfo> getAll() {

		List<CategoryInfo> listCategoryInfor = new ArrayList<CategoryInfo>();
		List<Category> listCategory = categoryRepository.findAll();

		for (Category category : listCategory) {
			CategoryInfo categoryInfo = new CategoryInfo();

			categoryInfo.setId(category.getId());
			categoryInfo.setName(category.getName());
			categoryInfo.setAvatar(category.getAvatar());
			categoryInfo.setDescription(category.getDescription());

			listCategoryInfor.add(categoryInfo);
		}

		return listCategoryInfor;

	}

	@Override
	public CategoryInfo getOneCategory(Long id) {
		Category oldCateogry = categoryRepository.findOneById(id);
		CategoryInfo categoryInfo = new CategoryInfo();

		if (oldCateogry != null) {

			categoryInfo.setId(oldCateogry.getId());
			categoryInfo.setName(oldCateogry.getName());
			categoryInfo.setAvatar(oldCateogry.getAvatar());
			categoryInfo.setDescription(oldCateogry.getDescription());

			return categoryInfo;
		} else {
			return null;
		}
	}

}
