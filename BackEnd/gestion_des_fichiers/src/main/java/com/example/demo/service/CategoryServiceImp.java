package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.models.Category;
import com.example.demo.models.SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;

@Component 
public class CategoryServiceImp implements CategoryService {
	@Autowired
	private SubCategoryRepository subcategoryRepository;
	@Autowired
	private CategoryRepository categorieRepository;
	

	


	public CategoryServiceImp(SubCategoryRepository subcategoryRepository) {
		super();
		this.subcategoryRepository = subcategoryRepository;
	}



	@Override
	public List<SubCategory> getSubCategoryByCategory(Category c) {

		List<SubCategory> list = subcategoryRepository.findAll();
		List<SubCategory> finallist = new ArrayList<SubCategory>();
		for (int i =0; i<list.size();i++) {
			if(list.get(i).getCategory().getId().equals(c.getId()))
				finallist.add(list.get(i));
			
		}
		return finallist;
	}

}
