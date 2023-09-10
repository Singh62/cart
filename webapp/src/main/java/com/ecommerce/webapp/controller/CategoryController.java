package com.ecommerce.webapp.controller;

import com.ecommerce.webapp.model.Category;
import com.ecommerce.webapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    public String createCategory(@RequestBody Category category){
        CategoryService.createCategory(category);
        return "success";
    }
}
