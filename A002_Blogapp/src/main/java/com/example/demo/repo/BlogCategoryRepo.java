package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BlogCategory;

public interface BlogCategoryRepo extends JpaRepository<BlogCategory, Integer>{

}
