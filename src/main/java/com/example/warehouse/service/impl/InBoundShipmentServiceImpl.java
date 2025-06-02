package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.InBoundShipmentMapper;
import com.example.warehouse.dto.request.InBoundShipmentRequest;
import com.example.warehouse.dto.response.InBoundShipmentResponse;
import com.example.warehouse.entity.InBoundShipment;
import com.example.warehouse.entity.WareHouse;
import com.example.warehouse.exceptions.WareHouseNotFindByIdException;
import com.example.warehouse.repository.InBoundShipmentRepository;
import com.example.warehouse.repository.WareHouseRepository;
import com.example.warehouse.service.contract.InBoundShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InBoundShipmentServiceImpl implements InBoundShipmentService {

    @Autowired
    private InBoundShipmentRepository inBoundShipmentRepository;

    @Autowired
    private InBoundShipmentMapper inBoundShipmentMapper;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Transactional
    @Override
    public InBoundShipmentResponse receiveProductInWareHouse(InBoundShipmentRequest request,String wareHouseId) {
       WareHouse wareHouse = wareHouseRepository.findById(wareHouseId).orElseThrow(()->new WareHouseNotFindByIdException("Warehouse not Found!!"));
       InBoundShipment inBoundShipment = inBoundShipmentMapper.toEntity(request,new InBoundShipment());
       inBoundShipment.setWarehouse(wareHouse);
       wareHouseRepository.save(wareHouse);
       inBoundShipmentRepository.save(inBoundShipment);
       return inBoundShipmentMapper.toResponse(inBoundShipment);
    }
}
