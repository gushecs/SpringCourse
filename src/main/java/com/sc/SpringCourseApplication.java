package com.sc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sc.domain.Category;
import com.sc.repositories.CategoryRepository;

@SpringBootApplication
public class SpringCourseApplication implements CommandLineRunner{

	@Autowired
	CategoryRepository categoryRep;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null,"computers");
		Category cat2 = new Category(null,"office");
		
		categoryRep.saveAll(Arrays.asList(cat1,cat2));
		
	}

}
