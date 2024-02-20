package com.trade_platform.Entity.Trade;

import com.trade_platform.Entity.Customer.Customer;
import com.trade_platform.Entity.Organization.Organization;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(
    name = "trade_request",
    indexes = @Index(name = "status_index", columnList = "status"),
    uniqueConstraints = @UniqueConstraint(name = "participant_lot_index", columnNames = {"id_participant", "id_lot"})
)
public class TradeRequest {
    public enum Status { NEW, BUYER_APPROVED, BUYER_REJECTED, PARTIALLY_SIGNED, SIGNED, REJECTED }

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

    @Column(name = "status", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "is_admitted_to_trading", nullable = false)
    private boolean isAdmittedToTrading = false;

    @Column(name = "deposit_expected", nullable = false)
    private double depositExpected;

    @Column(name = "deposit_actual", nullable = false)
    private double depositActual;

    @Column(name = "date_signed_at")
    private Date dateSignedAt;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    public TradeRequest(
        Trade trade,
        TradeLot lot,
        Organization participant,
        Customer customer
    ) {
        this.dateCreatedAt = new Date();
        this.trade = trade;
        this.lot = lot;
        this.participant = participant;
        this.customer = customer;
    }
}