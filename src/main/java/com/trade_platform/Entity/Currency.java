package com.trade_platform.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(
    name = "currency",
    indexes = @Index(name = "cc_index", columnList = "cc")
)
public class Currency {
    public static final String UAH = "UAH";
    public static final String USD = "USD";
    public static final String EUD = "EUR";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 63, nullable = false)
    private String name;

    @Column(name = "r030", nullable = false)
    private int r030;

    @Column(name = "cc", length = 7, nullable = false)
    private String cc;

    @OneToMany(targetEntity = CurrencyRate.class, mappedBy = "currency")
    private List<CurrencyRate> rates;

    public Currency() {
        this.rates = new ArrayList<>();
    }
}