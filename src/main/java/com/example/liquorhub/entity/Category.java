package com.example.liquorhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString(callSuper = true)
@Entity
@Table(name = "N_CATEGORIES", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@EqualsAndHashCode(callSuper = true)
public class Category extends Base {
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
