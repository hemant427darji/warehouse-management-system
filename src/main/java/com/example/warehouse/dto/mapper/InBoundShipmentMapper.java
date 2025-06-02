package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.request.InBoundShipmentRequest;
import com.example.warehouse.dto.request.ProductRequest;
import com.example.warehouse.dto.response.InBoundShipmentResponse;
import com.example.warehouse.dto.response.ProductResponse;
import com.example.warehouse.entity.InBoundShipment;
import org.springframework.stereotype.Controller;

@Controller
public class InBoundShipmentMapper {

    public InBoundShipment toEntity(InBoundShipmentRequest source, InBoundShipment target){
        target.setSellerId(source.sellerId());
        if (source.productDetails() != null) {
            ProductRequest productDetails = source.productDetails();
            target.setProductTile(productDetails.productTitle());
            target.setProductWeight(productDetails.productWeight());
            target.setProductLength(productDetails.productLength());
            target.setProductHeight(productDetails.productHeight());
            target.setProductWidth(productDetails.productWidth());
            target.setMaterialType(productDetails.materialType());
            target.setCareInstruction(productDetails.careInstruction());
            target.setQuantity(productDetails.quantity());
            target.setProductPrice(productDetails.productPrice());
        }
        target.setStatus(source.status());
        return target;
    }

    public InBoundShipmentResponse toResponse(InBoundShipment inBoundShipment){
        if (inBoundShipment == null) {
            return null;
        }
        ProductResponse productResponse = new ProductResponse(
                inBoundShipment.getProductTile(),
                inBoundShipment.getProductWeight(),
                inBoundShipment.getProductLength(),
                inBoundShipment.getProductHeight(),
                inBoundShipment.getProductWidth(),
                inBoundShipment.getMaterialType(),
                inBoundShipment.getCareInstruction(),
                inBoundShipment.getQuantity(),
                inBoundShipment.getProductPrice()
        );
        return new InBoundShipmentResponse(
                inBoundShipment.getShipmentId(),
                productResponse,
                inBoundShipment.getSellerId(),
                inBoundShipment.getCreatedAt().toEpochMilli(),
                inBoundShipment.getStatus()
        );
    }
}
