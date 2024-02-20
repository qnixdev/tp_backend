package com.trade_platform.Entity.Organization.Reference;

import com.trade_platform.Entity.Organization.Organization;
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
@Table(name = "ownership")
public class Ownership {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 63, nullable = false)
    private String name;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    @OneToMany(targetEntity = Organization.class, mappedBy = "ownership")
    private List<Organization> organizations;

    public Ownership() {
        this.dateCreatedAt = new Date();
        this.organizations = new ArrayList<>();
    }
}