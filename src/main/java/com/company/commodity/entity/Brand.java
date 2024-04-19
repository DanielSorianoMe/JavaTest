package com.company.commodity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BRANDS")
public class Brand {

    @Id
    @Column(name = "BRAND_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

}
