package com.trade_platform.Entity;

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
    name = "currency_rate",
    indexes = @Index(name = "date_exchange_index", columnList = "date_exchange_at")
)
public class CurrencyRate {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "id_currency", referencedColumnName = "id", nullable = false)
    private Currency currency;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    @Column(name = "date_exchange_at", nullable = false)
    private Date dateExchangeAt;

    @PrePersist
    public void prePersist() {
        this.dateCreatedAt = new Date();
    }
}