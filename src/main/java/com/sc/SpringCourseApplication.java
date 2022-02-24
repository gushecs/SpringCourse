package com.sc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sc.domain.Category;
import com.sc.domain.City;
import com.sc.domain.Product;
import com.sc.domain.State;
import com.sc.repositories.CategoryRepository;
import com.sc.repositories.CityRepository;
import com.sc.repositories.ProductRepository;
import com.sc.repositories.StateRepository;

@SpringBootApplication
public class SpringCourseApplication implements CommandLineRunner{

	@Autowired
	CategoryRepository categoryRep;
	
	@Autowired
	ProductRepository productRep;
	
	@Autowired
	CityRepository cityRep;
	
	@Autowired
	StateRepository stateRep;
	
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
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null,"Uberlândia",st1);
		City c2 = new City(null,"São Paulo",st2);
		City c3 = new City(null,"Campinas",st2);
		
		st1.getCities().add(c1);
		st2.getCities().addAll(Arrays.asList(c2,c3));
		
		stateRep.saveAll(Arrays.asList(st1,st2));
		cityRep.saveAll(Arrays.asList(c1,c2,c3));
		
	}

}
