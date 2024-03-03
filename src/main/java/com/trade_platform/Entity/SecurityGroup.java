package com.trade_platform.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.trade_platform.Entity.Customer.Customer;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "security_group")
public class SecurityGroup {
    public enum Group { ADMINISTRATOR, PARTICIPANT, AGENT }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "group", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Group group;

    @Column(name = "name", length = 63, nullable = false)
    private String name;

    @JsonManagedReference
    @ManyToMany(targetEntity = SecurityRole.class)
    @JoinTable(
        name = "groups_roles",
        joinColumns = @JoinColumn(name = "id_group", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id")
    )
    private Set<SecurityRole> securityRoles;

    @JsonBackReference
    @ManyToMany(targetEntity = Customer.class, mappedBy = "securityGroups")
    private Set<Customer> customers;

    @JsonBackReference
    @ManyToMany(targetEntity = User.class, mappedBy = "securityGroups")
    private Set<User> users;

    public SecurityGroup() {
        this.securityRoles = new HashSet<>();
        this.customers = new HashSet<>();
        this.users = new HashSet<>();
    }

    public void addSecurityRole(SecurityRole securityRole) {
        if (!this.securityRoles.contains(securityRole)) {
            this.securityRoles.add(securityRole);
            securityRole.addSecurityGroup(this);
        }
    }

    public void removeSecurityRole(SecurityRole securityRole) {
        if (this.securityRoles.remove(securityRole)) {
            securityRole.removeSecurityGroup(this);
        }
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        this.customers.remove(customer);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
}