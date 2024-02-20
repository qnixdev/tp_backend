package com.trade_platform.Entity.Trade.Reference;

import com.trade_platform.Entity.Trade.TradeItemReferenceData;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "delivery_terms")
public class DeliveryTerms extends AbstractReferenceEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(targetEntity = TradeItemReferenceData.class, mappedBy = "deliveryTerms", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<TradeItemReferenceData> tradeItemReferenceDataList;

    public DeliveryTerms() {
        this.tradeItemReferenceDataList = new ArrayList<>();
    }
}