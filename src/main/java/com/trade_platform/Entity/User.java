package com.trade_platform.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.trade_platform.Entity.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "`user`")
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @JsonManagedReference
    @ManyToMany(targetEntity = SecurityGroup.class)
    @JoinTable(
        name = "users_security_groups",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_security_group", referencedColumnName = "id")
    )
    private Set<SecurityGroup> securityGroups;

    public User() {
        this.dateCreatedAt = new Date();
        this.securityGroups = new HashSet<>();
    }

    public void addSecurityGroup(SecurityGroup securityGroup) {
        if (!this.securityGroups.contains(securityGroup)) {
            this.securityGroups.add(securityGroup);
            securityGroup.addUser(this);
        }
    }

    public void removeSecurityGroup(SecurityGroup securityGroup) {
        if (this.securityGroups.remove(securityGroup)) {
            securityGroup.removeUser(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getSecurityGroups()
            .stream()
            .flatMap(sg -> sg.getSecurityRoles().stream())
            .map(SecurityRole::getRole)
            .map(SimpleGrantedAuthority::new)
            .toList()
        ;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}