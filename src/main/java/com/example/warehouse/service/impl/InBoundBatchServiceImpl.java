package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.InBoundBatchMapper;
import com.example.warehouse.dto.mapper.ProductUnitMapper;
import com.example.warehouse.dto.request.InBoundBatchRequest;
import com.example.warehouse.dto.request.InventoryLocationUpdateRequest;
import com.example.warehouse.dto.response.InBoundBatchResponse;
import com.example.warehouse.dto.response.ProductUnitResponse;
import com.example.warehouse.entity.*;
import com.example.warehouse.exceptions.BlockNotFoundException;
import com.example.warehouse.exceptions.ShipmentIdNotExistException;
import com.example.warehouse.repository.*;
import com.example.warehouse.service.contract.InBoundBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InBoundBatchServiceImpl implements InBoundBatchService {

    @Autowired
    private InBoundBatchRepository inBoundBatchRepository;

    @Autowired
    private ProductUnitRepository productUnitRepository;

    @Autowired
    private InBoundBatchMapper inBoundBatchMapper;

    @Autowired
    private InBoundShipmentRepository inBoundShipmentRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private RackRepository rackRepository;

    @Autowired
    private ProductUnitMapper productUnitMapper;


    @Transactional
    @Override
    public InBoundBatchResponse receiveProductUnit(InBoundBatchRequest request, String shipmentId) {
        InBoundShipment inBoundShipment = inBoundShipmentRepository.findById(shipmentId).orElseThrow(() -> new ShipmentIdNotExistException("Shipment Not Created Yet!!"));
        Product product = inBoundShipment.getProduct();
        com.example.warehouse.entity.InBoundBatch inBoundBatch = inBoundBatchMapper.toEntity(request, new InBoundBatch());

        List<ProductUnit> productUnits = new ArrayList<>();

        for (int i = 0; i < inBoundBatch.getCountOfAcceptedUnit(); i++) {
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

    @Override
    public List<ProductUnitResponse> updateInventoryLocation(InventoryLocationUpdateRequest request) {
        Block block = blockRepository.findById(request.blockId()).orElseThrow(() -> new BlockNotFoundException("Invalid Block Id!!"));

        switch (block.getType()) {
            case RECKED -> {
                Rack rack = rackRepository.findById(request.rackId()).orElseThrow(() -> new BlockNotFoundException("Invalid Rack Id!!"));

                String suffix = "< " + rack.getRackId() + " > ;" + " < " + request.selfNo() + " >";
                return updateUnitLoc(request, block, suffix);
            }
            case UNRECKED -> {
               return updateUnitLoc(request, block, "");
            }
        }
        return null;
    }

    @Transactional
    private List<ProductUnitResponse> updateUnitLoc(InventoryLocationUpdateRequest request, Block block, String suffix) {
        String location = "< " + block.getRoom().getRoomId() + " > ;" + "< " + block.getBlockId() + " > ; " + "< " + block.getType() + " > ; " + suffix;

//        List<ProductUnit> units = productUnitRepository.findAllById(request.unitIds());
//        units.forEach(unit -> unit.setLocation(location));
//
//        productUnitRepository.saveAll(units);

        List<ProductUnit> updated = productUnitRepository.findAllById(request.unitIds())
                .stream()
                .peek(unit -> unit.setLocation(location))
                .toList();
        productUnitRepository.saveAll(updated);
        return productUnitMapper.toResponse(updated);
    }
}
