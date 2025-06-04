package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.RackMapper;
import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.entity.Block;
import com.example.warehouse.entity.Rack;
import com.example.warehouse.entity.ReckedBlock;
import com.example.warehouse.entity.Room;
import com.example.warehouse.enums.BlockType;
import com.example.warehouse.exceptions.BlockNotFoundException;
import com.example.warehouse.exceptions.UnSupportedBlockTypeException;
import com.example.warehouse.repository.BlockRepository;
import com.example.warehouse.repository.RackRepository;
import com.example.warehouse.service.contract.RackService;
import static com.example.warehouse.shared.BarCodeGeneration.*;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class RackServiceImpl implements RackService {

    @Autowired
    private RackRepository rackRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private RackMapper rackMapper;

    @Transactional
    @Override
    public RackResponse addRacks(RackRequest request, String blockId) {
      Block block = blockRepository.findById(blockId).orElseThrow(()->new BlockNotFoundException("Block is Not Exists!!"));
      Rack rack = rackMapper.toEntity(request,new Rack());
      if (block instanceof ReckedBlock reckedBlock) {
          rack.setReckedBlock(reckedBlock);
          blockRepository.save(block);
          rackRepository.save(rack);
          return rackMapper.toResponse(rack);
      }
      else
          throw new UnSupportedBlockTypeException("Block must be Recked Type!!");
    }

    @Override
    public byte[] generateBarcodeForRack(String rackId) {
        Rack rack = rackRepository.findById(rackId)
                .orElseThrow(() -> new RuntimeException("Rack not found"));
        ReckedBlock block = rack.getReckedBlock();
        Room room = block.getRoom();

        String content = String.format("""
        {
          "roomId": "%s",
          "blockId": "%s",
          "rackId": "%s"
        }
        """, room.getRoomId(), block.getBlockId(), rack.getRackId());
        byte[] bytes = null;

        try {
            bytes = barCodeGeneration(content, 120, 10);
            return bytes;
        } catch (IOException | WriterException e) {
            System.out.println("");
        }
        return bytes;
    }

}
