package com.devsuperior.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;

@RestController // Vai responder pela web
@RequestMapping(value = "/products") // Configuro o que será acessado
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/{id}") // Esse método sera acessado por nessa rota
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) { // Assim consigo conversar com o 'id'!
		ProductDTO dto = productService.findById(id);
		return ResponseEntity.ok(dto); // Assim eu consigo customizar
		// o 'ok' é o código 200
	}
	
	// Listando todos os produtos
	@GetMapping
	public ResponseEntity<Page<ProductDTO>>	findAll(Pageable pageable) { 
		Page<ProductDTO> dto = productService.findAll(pageable);
		return ResponseEntity.ok(dto);
	}
	
	// Salvando
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) { // Com isso eu digo que é o corpo da requisição que vai entrar no parâmetro
		dto = productService.insert(dto);
		// Para retornar o código 201 (recurso criado)
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	// Atualizando
	@PutMapping(value = "/{id}") 
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) { 
		dto = productService.update(id, dto);
		return ResponseEntity.ok(dto); 
	}
	
	
	
}
