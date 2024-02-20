package com.trade_platform.Entity.Customer.Reference;

import com.trade_platform.Entity.Customer.Customer;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "id_customer", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(name = "phone", length = 63, nullable = false)
    private String phone;
}