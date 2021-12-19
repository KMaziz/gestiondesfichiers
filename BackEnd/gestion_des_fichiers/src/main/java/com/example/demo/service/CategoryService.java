package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Category;
import com.example.demo.models.SubCategory;

public interface CategoryService {
	
	List<SubCategory> getSubCategoryByCategory(Category c);

}
