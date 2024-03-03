package com.trade_platform.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "security_role")
public class SecurityRole {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "role", length = 63, nullable = false)
    private String role;

    @Column(name = "slug", length = 63, nullable = false)
    private String slug;

    @ManyToMany(targetEntity = SecurityGroup.class, mappedBy = "securityRoles")
    private Set<SecurityGroup> securityGroups;

    public SecurityRole() {
        this.securityGroups = new HashSet<>();
    }

    public void addSecurityGroup(SecurityGroup securityGroup) {
        this.securityGroups.add(securityGroup);
    }

    public void removeSecurityGroup(SecurityGroup securityGroup) {
        this.securityGroups.remove(securityGroup);
    }
}