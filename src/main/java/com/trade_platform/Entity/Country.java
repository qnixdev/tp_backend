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
@Table(name = "country")
public class Country {
    private static final String CODE_UKRAINE = "UA";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 63, nullable = false)
    private String name;

    @Column(name = "alpha2_code", length = 7, nullable = false)
    private String alpha2Code;

    @OneToMany(targetEntity = Region.class, mappedBy = "country")
    private List<Region> regions;

    public Country() {
        this.regions = new ArrayList<>();
    }

    public void addRegion(Region region) {
        if (!this.regions.contains(region)) {
            this.regions.add(region);
            region.setCountry(this);
        }
    }

    public void removeRegion(Region region) {
        if (this.regions.remove(region)) {
            region.setCountry(null);
        }
    }
}