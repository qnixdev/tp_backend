package com.trade_platform.Entity.Trade;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "trade_type")
public class TradeType {
    public enum Type { AUCTION, REDUCTION }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "slug", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type slug;

    @Column(name = "name", length = 63, nullable = false)
    private String name;
}