package com.trade_platform.Entity.Trade;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "trade_lot_position")
public class TradeLotPosition {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = TradeLot.class)
    @JoinColumn(name = "id_trade", referencedColumnName = "id", nullable = false)
    private TradeLot lot;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "clarification", length = 511, nullable = false)
    private String clarification;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "number_position", nullable = false)
    private int numberPosition;

    @Column(name = "volume", nullable = false)
    private double volume;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "owner", nullable = false)
    private String owner;

    @OneToOne(targetEntity = TradeItemReferenceData.class, mappedBy = "position", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private TradeItemReferenceData referenceData = null;

    @PreUpdate
    public void preUpdate() {
        this.getLot().getTrade().setDateUpdatedAt(new Date());
    }
}