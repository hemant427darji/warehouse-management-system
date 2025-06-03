package com.example.warehouse.repository;

import com.example.warehouse.entity.InBoundBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InBoundBatchRepository extends JpaRepository<InBoundBatch , String> {
}
