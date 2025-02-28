package com.example.es280225.service;

import com.example.es280225.mapper.ProductMapper;
import com.example.es280225.object.dto.ProductDTO;
import com.example.es280225.object.model.Product;
import com.example.es280225.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productMapper.toProductDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product = productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        // Aggiorna i dettagli dell'entità con i dati dal ProductDTO
        existingProduct.setNome(productDTO.getNome());
        existingProduct.setPrezzo(productDTO.getPrezzo());

        existingProduct = productRepository.save(existingProduct);
        return productMapper.toProductDTO(existingProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
