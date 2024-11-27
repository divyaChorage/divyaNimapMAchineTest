package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Category;


public interface CategoryService {

	String saveCategory(Category cate);
	 public int deleteCategoryById(int id);
	 public Category updateCategoryById(int id,Category emp);
	 Category findCategoryById(int id);
	 List<Category> findCategoriesByName(String name);

	 List<Category> getAllCategory();
}
