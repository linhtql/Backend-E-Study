package com.estudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudy.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findOneByName(String name);

	Category findOneById(Long id);
}
