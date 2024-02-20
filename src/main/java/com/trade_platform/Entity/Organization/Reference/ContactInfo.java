package com.trade_platform.Entity.Organization.Reference;

import com.trade_platform.Entity.Organization.Organization;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "contact_info")
public class ContactInfo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(targetEntity = Organization.class)
    @JoinColumn(name = "id_organization", referencedColumnName = "id", nullable = false)
    private Organization organization;

    @Column(name = "email", length = 63)
    private String email;

    @Column(name = "phone", length = 63)
    private String phone;

    @Column(name = "fax", length = 63)
    private String fax;

    @Column(name = "web_site", length = 127)
    private String webSite;
}