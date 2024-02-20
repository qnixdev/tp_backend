package com.trade_platform.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "id_country", referencedColumnName = "id", nullable = false)
    private Country country;

    @Column(name = "name", length = 63, nullable = false)
    private String name;
}