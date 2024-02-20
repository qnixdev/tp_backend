package com.trade_platform.Entity.Organization.Reference;

import com.trade_platform.Entity.Currency;
import com.trade_platform.Entity.Organization.Organization;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "bank_info")
public class BankInfo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "id_organization", referencedColumnName = "id", nullable = false)
    private Organization organization;

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "id_currency", referencedColumnName = "id", nullable = false)
    private Currency currency;

    @Column(name = "mfo", length = 63)
    private String mfo;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_address")
    private String bankAddress;

    @Column(name = "iban", length = 63)
    private String iban;

    @Column(name = "swift_code", length = 63)
    private String swiftCode;

    @Column(name = "account_info", length = 511)
    private String accountInfo;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault = true;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    @Column(name = "date_updated_at")
    private Date dateUpdatedAt;

    public BankInfo() {
        this.dateCreatedAt = new Date();
    }
}