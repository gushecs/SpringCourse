package com.sc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sc.domain.Product;
import com.sc.domain.DTO.ProductDTO;
import com.sc.resources.util.Url;
import com.sc.services.ProductService;

@RestController
@RequestMapping (value = "/products")
public class ProductResource {
	
	@Autowired
	ProductService service;

	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> findProduct(@PathVariable Integer id) {		
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "lines", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "order", defaultValue = "name") String orderBy,
			@RequestParam(value = "dir", defaultValue = "ASC") String direction,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories) {
		List<Integer> ids = Url.decodeIntList(categories);
		String decodedName = Url.decodeParam(name);
		return ResponseEntity.ok()
				.body(service.search(decodedName,ids,page, linesPerPage, orderBy, direction).map(obj -> new ProductDTO(obj)));
	}
	
}
