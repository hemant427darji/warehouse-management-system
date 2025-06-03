package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.response.ProductUnitResponse;
import com.example.warehouse.entity.ProductUnit;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductUnitMapper {

    public List<ProductUnitResponse> toResponse(List<ProductUnit> units) {
        return units.stream().map(unit -> new ProductUnitResponse(
                unit.getUnitId(),
                unit.getLocation(),
                unit.getProduct() != null ? unit.getProduct().getProductId() : null,
                unit.getInBoundShipment() != null ? unit.getInBoundShipment().getShipmentId() : null,
                unit.getBatch() != null ? unit.getBatch().getBatchId() : null
        )).toList();
    }
}
