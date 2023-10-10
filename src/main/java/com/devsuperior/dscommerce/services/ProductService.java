package com.devsuperior.dscommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	// o retorno precisa ser DTO porque esse dado irá para o controller
	@Transactional(readOnly = true) // Assim digo que estou apenas lendo(fica mais rápido)
	public ProductDTO findById(Long id) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get(); // Assim que salvo
		// Product product = repository.findById(id).get(); // Outro jeito
		ProductDTO productDTO = new ProductDTO(product);
		return productDTO;
		// return new ProductDTO(product); // outra forma
	}
	
	@Transactional(readOnly = true) 
	public Page<ProductDTO> findAll(Pageable pageable) { // Assim consigo listar de forma listada (não tudo)
		Page<Product> result = repository.findAll(pageable); // Vai retornar todos os registros da entidade product.
		return result.map(x -> new ProductDTO(x)); // Converti para ProductDO (Lambda)
	}
}
