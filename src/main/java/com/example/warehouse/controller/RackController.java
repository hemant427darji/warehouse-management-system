package com.example.warehouse.controller;

import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.RackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/barcode")
    public ResponseEntity<byte[]> generateBarcodeForRack(@RequestParam String rackId) {
        byte[] data = rackService.generateBarcodeForRack(rackId);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG.toString())
               .body(data);
    }
}
