package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.InBoundBatchMapper;
import com.example.warehouse.dto.request.InBoundBatchRequest;
import com.example.warehouse.dto.response.InBoundBatchResponse;
import com.example.warehouse.entity.InBoundBatch;
import com.example.warehouse.entity.InBoundShipment;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.ProductUnit;
import com.example.warehouse.exceptions.ShipmentIdNotExistException;
import com.example.warehouse.repository.InBoundBatchRepository;
import com.example.warehouse.repository.InBoundShipmentRepository;
import com.example.warehouse.repository.ProductUnitRepository;
import com.example.warehouse.service.contract.InBoundBatchServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InBoundBatchServiceImpl implements InBoundBatchServive {

    @Autowired
    private InBoundBatchRepository inBoundBatchRepository;

    @Autowired
    private ProductUnitRepository productUnitRepository;

    @Autowired
    private InBoundBatchMapper inBoundBatchMapper;

    @Autowired
    private InBoundShipmentRepository inBoundShipmentRepository;

    @Transactional
    @Override
    public InBoundBatchResponse receiveProductUnit(InBoundBatchRequest request, String shipmentId) {
       InBoundShipment inBoundShipment = inBoundShipmentRepository.findById(shipmentId).orElseThrow(()->new ShipmentIdNotExistException("Shipment Not Created Yet!!"));
        Product product = inBoundShipment.getProduct();
        com.example.warehouse.entity.InBoundBatch inBoundBatch = inBoundBatchMapper.toEntity(request, new InBoundBatch());

        List<ProductUnit> productUnits = new ArrayList<>();

        for (int i=0;i<inBoundBatch.getCountOfAcceptedUnit();i++){
            ProductUnit unit = new ProductUnit();
            unit.setProduct(product);
            unit.setInBoundShipment(inBoundShipment);
            unit.setBatch(inBoundBatch);
            productUnits.add(unit);
        }
        productUnitRepository.saveAll(productUnits);
        inBoundBatch.setProductUnitList(productUnits);
        inBoundBatchRepository.save(inBoundBatch);
        return inBoundBatchMapper.toResponse(inBoundBatch);
    }
}
