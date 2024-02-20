package com.trade_platform.Entity.Trade;

import com.trade_platform.Entity.Customer.Customer;
import com.trade_platform.Entity.Organization.Organization;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(
    name = "trade_lot_bet",
    uniqueConstraints = @UniqueConstraint(name = "lot_bet_index", columnNames = {"id_lot", "bet"})
)
public class TradeLotBet {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Trade.class)
    @JoinColumn(name = "id_trade", referencedColumnName = "id", nullable = false)
    private Trade trade;

    @ManyToOne(targetEntity = TradeLot.class)
    @JoinColumn(name = "id_lot", referencedColumnName = "id", nullable = false)
    private TradeLot lot;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "id_participant", referencedColumnName = "id", nullable = false)
    private Organization participant;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "id_customer", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(name = "bet", nullable = false)
    private double bet;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    @Column(name = "date_finished_at")
    private Date dateFinishedAt;

    @Column(name = "date_check")
    private Long dateCheck;

    public TradeLotBet() {
        this.dateCreatedAt = new Date();
    }
}