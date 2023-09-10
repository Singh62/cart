package com.ecommerce.webapp.service;

import com.ecommerce.webapp.model.Category;
import com.ecommerce.webapp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public void createCategory(Category category){
        categoryRepo.save(category);
    }
}
