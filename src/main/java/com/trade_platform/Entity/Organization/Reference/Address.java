package com.trade_platform.Entity.Organization.Reference;

import com.trade_platform.Entity.Country;
import com.trade_platform.Entity.Organization.Organization;
import com.trade_platform.Entity.Region;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "address")
public class Address {
    public enum Type { LEGAL, POST }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "id_organization", referencedColumnName = "id", nullable = false)
    private Organization organization;

    @Column(name = "type", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "id_country", referencedColumnName = "id", nullable = false)
    private Country country;

    @ManyToOne(targetEntity = Region.class)
    @JoinColumn(name = "id_region", referencedColumnName = "id")
    private Region region = null;

    @Column(name = "city", length = 127)
    private String city;

    @Column(name = "district", length = 127)
    private String district;

    @Column(name = "street", length = 127)
    private String street;

    @Column(name = "build", length = 63)
    private String build;

    @Column(name = "section", length = 63)
    private String section;

    @Column(name = "office", length = 63)
    private String office;

    @Column(name = "zip_code", length = 63)
    private String zipCode;

    public String getFullAddress() {
        return String.format("%1$s%2$s%3$s%4$s%5$s%6$s%7$s%8$s%9$s",
            this.getCountry().getName(),
            null != this.getRegion() ? String.format(", %s", this.getRegion().getName()) : "",
            null != this.city ? String.format(", %s", this.getCity()) : "",
            null != this.district ? String.format(", %s", this.getDistrict()) : "",
            null != this.street ? String.format(", %s", this.getStreet()) : "",
            null != this.build ? String.format(", %s", this.getBuild()) : "",
            null != this.section ? String.format(", %s", this.getSection()) : "",
            null != this.office ? String.format(", %s", this.getOffice()) : "",
            null != this.zipCode ? String.format(", %s", this.getZipCode()) : ""
        );
    }
}