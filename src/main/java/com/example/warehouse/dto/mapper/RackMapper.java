package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.entity.Rack;
import org.springframework.stereotype.Controller;

@Controller
public class RackMapper {

    public Rack toEntity(RackRequest source, Rack target){
        target.setHeight(source.height());
        target.setWidth(source.width());
        target.setBreath(source.breath());
        return target;
    }

    public RackResponse toResponse(Rack rack){
        return new RackResponse(
                rack.getRackId(),
                rack.getHeight(),
                rack.getWidth(),
                rack.getBreath()
        );
    }
}
