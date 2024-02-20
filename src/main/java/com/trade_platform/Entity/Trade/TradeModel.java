package com.trade_platform.Entity.Trade;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "trade_model")
public class TradeModel {
    public enum Model { GENERAL, ADDITIONAL }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "slug", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Model slug;

    @Column(name = "name", length = 63, nullable = false)
    private String name;
}