package com.trade_platform.Entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.trade_platform.Entity.Organization.Organization;
import com.trade_platform.Service.Converter.CategoryTypeConverter;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "category")
public class Category {
    @AllArgsConstructor
    @Getter
    public enum Type {
        WOOD("wood"), LUMBER("lumber"), BROKER("broker"), EXPORT("export");

        @JsonValue
        public final String slug;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type", length = 63, nullable = false)
    @Convert(converter = CategoryTypeConverter.class)
    private Type type;

    @Column(name = "name", length = 63, nullable = false)
    private String name;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    @ManyToMany(targetEntity = Organization.class, mappedBy = "categories")
    private Set<Organization> organizations;

    public void addOrganization(Organization organization) {
        this.organizations.add(organization);
    }

    public void removeOrganization(Organization organization) {
        this.organizations.remove(organization);
    }

    @PrePersist
    public void prePersist() {
        this.dateCreatedAt = new Date();
    }
}