package com.example.warehouse.repository;

import com.example.warehouse.entity.InBoundShipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InBoundShipmentRepository extends JpaRepository<InBoundShipment,String> {

}
