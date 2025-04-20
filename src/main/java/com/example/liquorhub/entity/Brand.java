package com.example.liquorhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@Entity
@Table(name = "N_BRANDS", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@EqualsAndHashCode(callSuper = true)
public class Brand extends Base {

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

}
