package com.sc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sc.domain.Category;
import com.sc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Transactional(readOnly = true)
	Page<Product> findDistinctByNameContainingAndCategoriesIn (String name, List<Category> categories, Pageable request);
	
}
