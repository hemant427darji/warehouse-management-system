package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.InBoundShipmentMapper;
import com.example.warehouse.dto.request.InBoundShipmentRequest;
import com.example.warehouse.dto.response.InBoundShipmentResponse;
import com.example.warehouse.entity.InBoundShipment;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.WareHouse;
import com.example.warehouse.exceptions.WareHouseNotFindByIdException;
import com.example.warehouse.repository.InBoundShipmentRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.WareHouseRepository;
import com.example.warehouse.service.contract.InBoundShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InBoundShipmentServiceImpl implements InBoundShipmentService {

    @Autowired
    private InBoundShipmentRepository inBoundShipmentRepository;

    @Autowired
    private InBoundShipmentMapper inBoundShipmentMapper;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public InBoundShipmentResponse receiveProductInWareHouse(InBoundShipmentRequest request, String warehouseId) {
        Product product = productRepository.findById(request.productDetails().productId())
                .orElse(null);

        if (product == null) {
            product = inBoundShipmentMapper.productToEntity(request.productDetails());
            product = productRepository.save(product);
        }

        WareHouse warehouse = wareHouseRepository.findById(warehouseId)
                .orElseThrow(() -> new WareHouseNotFindByIdException("Warehouse not found!"));

        InBoundShipment inBoundShipment = inBoundShipmentMapper.toEntity(request, new InBoundShipment());
        inBoundShipment.setProduct(product);
        inBoundShipment.setWarehouse(warehouse);
        inBoundShipmentRepository.save(inBoundShipment);
        return inBoundShipmentMapper.toResponse(inBoundShipment);
    }


}
