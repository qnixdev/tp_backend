package com.trade_platform.Entity.Organization.Reference;

import com.trade_platform.Entity.Organization.Organization;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "business_form")
public class BusinessForm {
    @AllArgsConstructor
    @Getter
    public enum Type {
        LEGAL_ENTITY("jp"), INDIVIDUAL_ENTREPRENEUR("fop"), AFFILIATE("affiliate");

        private final String slug;

        public static Map<String, String> list() {
            return Arrays.stream(Type.values()).collect(Collectors.toMap(Enum::name, Type::getSlug));
        }
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "name", length = 63, nullable = false)
    private String name;

    @OneToMany(targetEntity = Organization.class, mappedBy = "businessForm")
    private List<Organization> organizations;

    public BusinessForm() {
        this.organizations = new ArrayList<>();
    }
}