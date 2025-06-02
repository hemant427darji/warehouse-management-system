package com.example.warehouse.controller;

import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.RackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RackController {

    @Autowired
    private RackService rackService;

    @PostMapping("/racks/{blockId}")
    public ResponseEntity<ResponseStructure<RackResponse>> addRacks(@RequestBody RackRequest request, @PathVariable String blockId){
      RackResponse rackResponse = rackService.addRacks(request,blockId);
      ResponseStructure<RackResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(),"Rack Added Successfully!!",rackResponse);
      return new ResponseEntity<ResponseStructure<RackResponse>>(responseStructure,HttpStatus.CREATED);
    }
}
