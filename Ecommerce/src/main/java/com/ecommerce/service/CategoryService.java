package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

	CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public void delCategorybyId(int id) {
		categoryRepository.deleteById(id);
	}

	public Category getCategorybyId(int id) {
		return categoryRepository.findById(id).get();
	}

	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}

}
