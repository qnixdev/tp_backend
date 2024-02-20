package com.trade_platform.Entity.Organization.Reference;

import com.trade_platform.Entity.Organization.Organization;
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
@Table(name = "employee")
public class Employee {
    public enum Type { DIRECTOR, GENERAL_ACCOUNTANT }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "full_name", length = 127, nullable = false)
    private String fullName;

    @Column(name = "position", length = 127)
    private String position;

    @Column(name = "email", length = 63)
    private String email;

    @Column(name = "phone", length = 63)
    private String phone;

    @Column(name = "date_expired_at")
    private Date dateExpiredAt;

    @ManyToMany(targetEntity = Organization.class, mappedBy = "employees")
    private Set<Organization> organizations;

    public void addOrganization(Organization organization) {
        this.organizations.add(organization);
    }

    public void removeOrganization(Organization organization) {
        this.organizations.remove(organization);
    }
}