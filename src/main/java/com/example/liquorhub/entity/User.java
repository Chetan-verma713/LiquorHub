package com.example.liquorhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "N_USERS", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL_ADDRESS"))
@EqualsAndHashCode(callSuper = true)
public class User extends Base {
    @Column(name = "EMAIL_ADDRESS")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

}
