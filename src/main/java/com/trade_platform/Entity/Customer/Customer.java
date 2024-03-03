package com.trade_platform.Entity.Customer;

import com.trade_platform.Entity.Customer.Reference.*;
import com.trade_platform.Entity.Enum.Status;
import com.trade_platform.Entity.Organization.Organization;
import com.trade_platform.Entity.SecurityGroup;
import com.trade_platform.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "id_organization", referencedColumnName = "id", nullable = false)
    private Organization organization;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_created_user", referencedColumnName = "id")
    private User createdUser = null;

    @Column(name = "status", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "api_token", unique = true)
    private String apiToken;

    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked = false;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    @Column(name = "date_updated_at")
    private Date dateUpdatedAt;

    @Column(name = "date_last_activity_at")
    private Date dateLastActivityAt;

    @Column(name = "date_deleted_at")
    private Date dateDeletedAt;

    @OneToMany(targetEntity = Phone.class, mappedBy = "customer", orphanRemoval = true)
    private List<Phone> phones;

    @ManyToMany(targetEntity = SecurityGroup.class)
    @JoinTable(
        name = "customers_security_groups",
        joinColumns = @JoinColumn(name = "id_customer", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_security_group", referencedColumnName = "id")
    )
    private Set<SecurityGroup> securityGroups;

    public Customer() {
        this.dateCreatedAt = new Date();
        this.phones = new ArrayList<>();
        this.securityGroups = new HashSet<>();
    }

    public void addPhone(Phone phone) {
        if (!this.phones.contains(phone)) {
            this.phones.add(phone);
            phone.setCustomer(this);
        }
    }

    public void removePhone(Phone phone) {
        if (this.phones.remove(phone)) {
            phone.setCustomer(null);
        }
    }

    public void addSecurityGroup(SecurityGroup securityGroup) {
        if (!this.securityGroups.contains(securityGroup)) {
            this.securityGroups.add(securityGroup);
            securityGroup.addCustomer(this);
        }
    }

    public void removeSecurityGroup(SecurityGroup securityGroup) {
        if (this.securityGroups.remove(securityGroup)) {
            securityGroup.removeCustomer(this);
        }
    }
}