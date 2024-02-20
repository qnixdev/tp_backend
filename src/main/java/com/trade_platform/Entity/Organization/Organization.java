package com.trade_platform.Entity.Organization;

import com.trade_platform.Entity.Category;
import com.trade_platform.Entity.Customer.Customer;
import com.trade_platform.Entity.Enum.Status;
import com.trade_platform.Entity.Organization.Reference.*;
import com.trade_platform.Entity.Trade.Trade;
import com.trade_platform.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "organization")
public class Organization {
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "usreou", length = 63, nullable = false)
    private String usreou;

    @Column(name = "is_resident", nullable = false)
    private boolean isResident = true;

    @ManyToOne(targetEntity = BusinessForm.class)
    @JoinColumn(name = "id_business_form", referencedColumnName = "id")
    private BusinessForm businessForm = null;

    @ManyToOne(targetEntity = Ownership.class)
    @JoinColumn(name = "id_ownership", referencedColumnName = "id")
    private Ownership ownership = null;

    @ManyToOne(targetEntity = TaxationType.class)
    @JoinColumn(name = "id_taxation_type", referencedColumnName = "id")
    private TaxationType taxationType = null;

    @Column(name = "pdv", length = 63)
    private String pdv;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    @Column(name = "date_updated_at")
    private Date dateUpdatedAt;

    @Column(name = "date_deleted_at")
    private Date dateDeletedAt;

    @Column(name = "date_registration_at")
    private Date dateRegistrationAt;

    @Column(name = "affiliate_number", length = 63)
    private String affiliateNumber;

    @Column(name = "is_broker", nullable = false)
    private boolean isBroker = false;

    @OneToMany(targetEntity = Customer.class, mappedBy = "organization", orphanRemoval = true)
    private List<Customer> customers;

    @OneToMany(targetEntity = Address.class, mappedBy = "organization", orphanRemoval = true)
    private List<Address> addresses;

    @OneToMany(targetEntity = BankInfo.class, mappedBy = "organization", orphanRemoval = true)
    private List<BankInfo> bankInfoList;

    @OneToOne(targetEntity = ContactInfo.class, mappedBy = "organization", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private ContactInfo contactInfo = null;

    @ManyToMany(targetEntity = Employee.class)
    @JoinTable(
        name = "organizations_employees",
        joinColumns = @JoinColumn(name = "id_organization", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_employee", referencedColumnName = "id")
    )
    private Set<Employee> employees;

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(
        name = "organizations_categories",
        joinColumns = @JoinColumn(name = "id_organization", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id")
    )
    private Set<Category> categories;

    @ManyToMany(targetEntity = Trade.class, mappedBy = "participants")
    private Set<Trade> trades;

    public Organization() {
        this.dateCreatedAt = new Date();
        this.customers = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.bankInfoList = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        if (!this.customers.contains(customer)) {
            this.customers.add(customer);
            customer.setOrganization(this);
        }
    }

    public void removeCustomer(Customer customer) {
        if (this.customers.remove(customer)) {
            customer.setOrganization(null);
        }
    }

    public void addAddress(Address address) {
        if (!this.addresses.contains(address)) {
            this.addresses.add(address);
            address.setOrganization(this);
        }
    }

    public void removeAddress(Address address) {
        if (this.addresses.remove(address)) {
            address.setOrganization(null);
        }
    }

    public void addBankInfo(BankInfo bankInfo) {
        if (!this.bankInfoList.contains(bankInfo)) {
            this.bankInfoList.add(bankInfo);
            bankInfo.setOrganization(this);
        }
    }

    public void removeBankInfo(BankInfo bankInfo) {
        if (this.bankInfoList.remove(bankInfo)) {
            bankInfo.setOrganization(null);
        }
    }

    public void addEmployee(Employee employee) {
        if (!this.employees.contains(employee)) {
            this.employees.add(employee);
            employee.addOrganization(this);
        }
    }

    public void removeEmployee(Employee employee) {
        if (this.employees.remove(employee)) {
            employee.removeOrganization(this);
        }
    }

    public void addCategory(Category category) {
        if (!this.categories.contains(category)) {
            this.categories.add(category);
            category.addOrganization(this);
        }
    }

    public void removeCategory(Category category) {
        if (this.categories.remove(category)) {
            category.removeOrganization(this);
        }
    }

    public void addTrade(Trade trade) {
        this.trades.add(trade);
    }

    public void removeTrade(Trade trade) {
        this.trades.remove(trade);
    }
}