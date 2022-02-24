package com.sc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sc.domain.Category;
import com.sc.domain.Product;
import com.sc.repositories.CategoryRepository;
import com.sc.repositories.ProductRepository;

@SpringBootApplication
public class SpringCourseApplication implements CommandLineRunner{

	@Autowired
	CategoryRepository categoryRep;
	
	@Autowired
	ProductRepository productRep;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null,"computers");
		Category cat2 = new Category(null,"office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().add(p2);
		p1.getCategories().add(cat1);
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().add(cat1);
		
		categoryRep.saveAll(Arrays.asList(cat1,cat2));
		productRep.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}
