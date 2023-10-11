package com.devsuperior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	// READ ID
	// o retorno precisa ser DTO porque esse dado irá para o controller
	@Transactional(readOnly = true) // Assim digo que estou apenas lendo(fica mais rápido)
	public ProductDTO findById(Long id) {
		// O optional tem try catch dentro
		Product product = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ProductDTO(product); 
	}
	
	// READL ALL PRODUCTS
	@Transactional(readOnly = true) 
	public Page<ProductDTO> findAll(Pageable pageable) { // Assim consigo listar de forma listada (não tudo)
		Page<Product> result = repository.findAll(pageable); // Vai retornar todos os registros da entidade product.
		return result.map(x -> new ProductDTO(x)); // Converti para ProductDO (Lambda)
	}
	
	// POST
	@Transactional
	public ProductDTO insert(ProductDTO dto) { 
		Product entity = new Product(); // Não é monitrado pela JPA
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity); // É assim que se salva no BD
		return new ProductDTO(entity); // Retorno em formato DTO
	}
	
	// PUT
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) { // Id + o corpo (body)
		Product entity = repository.getReferenceById(id); 
		// Não vai no banco de dados (é monitorado pala JPA), é uma referência
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity); // É assim que se salva no BD
		return new ProductDTO(entity); // Retorno em formato DTO
	}
	
	// DELETE
	@Transactional // Assim digo que estou apenas lendo(fica mais rápido)
	public void delete(Long id) {
		repository.deleteById(id);
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}
}
