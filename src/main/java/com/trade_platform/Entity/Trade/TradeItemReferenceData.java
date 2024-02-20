package com.trade_platform.Entity.Trade;

import com.trade_platform.Entity.Trade.Reference.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "trade_item_reference_data")
public class TradeItemReferenceData {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Trade.class)
    @JoinColumn(name = "id_trade", referencedColumnName = "id", nullable = false)
    private Trade trade;

    @OneToOne(targetEntity = TradeLot.class)
    @JoinColumn(name = "id_lot", referencedColumnName = "id")
    private TradeLot lot;

    @OneToOne(targetEntity = TradeLotPosition.class)
    @JoinColumn(name = "id_position", referencedColumnName = "id")
    private TradeLotPosition position;

    @ManyToOne(targetEntity = Measure.class)
    @JoinColumn(name = "id_measure", referencedColumnName = "id", nullable = false)
    private Measure measure;

    @ManyToOne(targetEntity = Species.class)
    @JoinColumn(name = "id_species", referencedColumnName = "id")
    private Species species = null;

    @ManyToOne(targetEntity = Assortment.class)
    @JoinColumn(name = "id_assortment", referencedColumnName = "id")
    private Assortment assortment = null;

    @ManyToOne(targetEntity = QualityClass.class)
    @JoinColumn(name = "id_quality_class", referencedColumnName = "id")
    private QualityClass qualityClass = null;

    @ManyToOne(targetEntity = Warehouse.class)
    @JoinColumn(name = "id_warehouse", referencedColumnName = "id")
    private Warehouse warehouse = null;

    @ManyToOne(targetEntity = Diameter.class)
    @JoinColumn(name = "id_diameter", referencedColumnName = "id")
    private Diameter diameter = null;

    @ManyToOne(targetEntity = Length.class)
    @JoinColumn(name = "id_length", referencedColumnName = "id")
    private Length length = null;

    @ManyToOne(targetEntity = Width.class)
    @JoinColumn(name = "id_width", referencedColumnName = "id")
    private Width width = null;

    @ManyToOne(targetEntity = Height.class)
    @JoinColumn(name = "id_height", referencedColumnName = "id")
    private Height height = null;

    @ManyToOne(targetEntity = Humidity.class)
    @JoinColumn(name = "id_humidity", referencedColumnName = "id")
    private Humidity humidity = null;

    @ManyToOne(targetEntity = DeliveryTerms.class)
    @JoinColumn(name = "id_delivery_terms", referencedColumnName = "id")
    private DeliveryTerms deliveryTerms = null;
}