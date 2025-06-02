package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "recked_block")
@Getter
@Setter
public class ReckedBlock extends Block{

    @OneToMany(mappedBy = "reckedBlock")
    private List<Rack> racks;

}
