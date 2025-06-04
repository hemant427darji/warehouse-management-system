package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.ProductMapper;
import com.example.warehouse.dto.response.ProductDetailsResponse;
import com.example.warehouse.entity.Product;
import com.example.warehouse.exceptions.ProductNotFoundException;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.contract.FetchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchProductServiceImpl implements FetchProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDetailsResponse fetchProductQuantity(String productId) {
     Product product = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product Not Found!!"));
     Long productQuantity = product.getUnit().stream().map(p->p.getUnitId()).count();
     return productMapper.toResponse(product,productQuantity);
    }
}
