package com.example.warehouse.repository;

import com.example.warehouse.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface RackRepository extends JpaRepository<Rack,String> {
}
