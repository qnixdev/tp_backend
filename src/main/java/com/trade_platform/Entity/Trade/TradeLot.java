package com.trade_platform.Entity.Trade;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "trade_lot")
public class TradeLot {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Trade.class)
    @JoinColumn(name = "id_trade", referencedColumnName = "id", nullable = false)
    private Trade trade;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "clarification", length = 511)
    private String clarification;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "number_position", nullable = false)
    private int numberPosition;

    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked = false;

    @Column(name = "volume", nullable = false)
    private double volume;

    @Column(name = "price")
    private Double price;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "owner", nullable = false)
    private String owner;

    @OneToOne(targetEntity = TradeItemReferenceData.class, mappedBy = "lot", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private TradeItemReferenceData referenceData = null;

    @OneToMany(targetEntity = TradeLotPosition.class, mappedBy = "lot")
    @OrderBy("numberPosition ASC")
    private List<TradeLotPosition> positions;

    @OneToMany(targetEntity = TradeLotBet.class, mappedBy = "lot")
    @OrderBy("bet DESC")
    private List<TradeLotBet> bets;

    @OneToOne(targetEntity = TradeLotBet.class)
    @JoinColumn(name = "id_best_bet", referencedColumnName = "id")
    private TradeLotBet bestBet = null;

    public TradeLot() {
        this.bets = new ArrayList<>();
        this.positions = new ArrayList<>();
    }

    public void addTradeLotBet(TradeLotBet bet) {
        if (!this.bets.contains(bet)) {
            this.bets.add(bet);
            bet.setLot(this);
        }
    }

    public void removeTradeLotBet(TradeLotBet bet) {
        if (this.bets.remove(bet)) {
            bet.setLot(null);
        }
    }

    public void addTradeLotPosition(TradeLotPosition position) {
        if (!this.positions.contains(position)) {
            this.positions.add(position);
            position.setLot(this);
        }
    }

    public void removeTradeLotPosition(TradeLotPosition position) {
        if (this.positions.remove(position)) {
            position.setLot(null);
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.getTrade().setDateUpdatedAt(new Date());
    }
}