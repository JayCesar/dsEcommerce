package com.devsuperior.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;

@RestController // Vai responder pela web
@RequestMapping(value = "/products") // Configuro o que será acessado
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/{id}") // Esse método sera acessado por nessa rota
	public ProductDTO findById(@PathVariable Long id) { // Assim consigo conversar com o 'id'!
		return productService.findById(id);
	}
	
	
	
}
