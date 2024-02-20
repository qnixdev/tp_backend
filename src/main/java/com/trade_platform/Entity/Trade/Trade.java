package com.trade_platform.Entity.Trade;

import com.trade_platform.Entity.Category;
import com.trade_platform.Entity.Customer.Customer;
import com.trade_platform.Entity.Organization.Organization;
import com.trade_platform.Entity.Region;
import com.trade_platform.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "trade")
public class Trade {
    @AllArgsConstructor
    @Getter
    public enum Status {
        CREATE_PASSPORT("Перегляд паспорту аукціону"),
        CREATE_PREPARE_LOTS("Підготовка лотів"),
        OFFERS_PROCESSING("Прийом заявок"),
        OFFERS_END("Прийом пропозицій завершено"),
        IN_PROGRESS("Торги"),
        IN_PROGRESS_OVERTIME("Основний час торгів вийшов"),
        PAUSED("Торги на паузі"),
        CHECK_WINNER("Вибір рішення"),
        FINISHED("Результати обробляються, очікуйте"),
        DONE("Торги завершено"),
        RESUMED("Торги відновлено");

        private final String name;
    }

    public static final int PERCENT_TRADE = 5, LIMIT_END = 3, LIMIT_PLUS = 3;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_created_user", referencedColumnName = "id")
    private User createdUser = null;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "id_created_customer", referencedColumnName = "id")
    private Customer createdCustomer = null;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "id_created_organization", referencedColumnName = "id")
    private Organization createdOrganization = null;

    @Column(name = "status", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "title", length = 511, nullable = false)
    private String title;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne(targetEntity = TradeType.class)
    @JoinColumn(name = "id_trade_type", referencedColumnName = "id", nullable = false)
    private TradeType tradeType;

    @ManyToOne(targetEntity = TradeModel.class)
    @JoinColumn(name = "id_trade_model", referencedColumnName = "id", nullable = false)
    private TradeModel tradeModel;

    @ManyToOne(targetEntity = TradeSpecification.class)
    @JoinColumn(name = "id_trade_specification", referencedColumnName = "id", nullable = false)
    private TradeSpecification tradeSpecification;

    @Column(name = "step", nullable = false)
    private float step;

    @Column(name = "quarter", nullable = false)
    private byte quarter;

    @Column(name = "date_offer_start_at", nullable = false)
    private Date dateOfferStartAt;

    @Column(name = "date_offer_end_at", nullable = false)
    private Date dateOfferEndAt;

    @Column(name = "date_trade_start_at", nullable = false)
    private Date dateTradeStartAt;

    @Column(name = "date_trade_end_at", nullable = false)
    private Date dateTradeEndAt;

    @Column(name = "date_paused_at")
    private Date datePausedAt;

    @Column(name = "limit_plus")
    private Short limitPlus;

    @Column(name = "commission")
    private Float commission;

    @Column(name = "commission_agent")
    private Float commissionAgent;

    @Column(name = "is_auto_change_status", nullable = false)
    private boolean isAutoChangeStatus = true;

    @ManyToOne(targetEntity = Region.class)
    @JoinColumn(name = "id_region", referencedColumnName = "id")
    private Region region = null;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt;

    @Column(name = "date_updated_at")
    private Date dateUpdatedAt;

    @Column(name = "date_deleted_at")
    private Date dateDeletedAt;

    @OneToMany(targetEntity = TradeLot.class, mappedBy = "trade", orphanRemoval = true)
    private List<TradeLot> lots;

    @OneToMany(targetEntity = TradeRequest.class, mappedBy = "trade", orphanRemoval = true)
    private List<TradeRequest> requests;

    @ManyToMany(targetEntity = Organization.class)
    @JoinTable(
        name = "trades_participants",
        joinColumns = @JoinColumn(name = "id_trade", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_participant", referencedColumnName = "id")
    )
    private Set<Organization> participants;

    public Trade() {
        this.dateCreatedAt = new Date();
        this.lots = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public void addTradeLot(TradeLot lot) {
        if (!this.lots.contains(lot)) {
            this.lots.add(lot);
            lot.setTrade(this);
        }
    }

    public void removeTradeLot(TradeLot lot) {
        if (this.lots.remove(lot)) {
            lot.setTrade(null);
        }
    }

    public void addTradeRequest(TradeRequest request) {
        if (!this.requests.contains(request)) {
            this.requests.add(request);
            request.setTrade(this);
        }
    }

    public void removeTradeRequest(TradeRequest request) {
        if (this.requests.remove(request)) {
            request.setTrade(null);
        }
    }

    public void addParticipant(Organization participant) {
        if (!this.participants.contains(participant)) {
            this.participants.add(participant);
            participant.addTrade(this);
        }
    }

    public void removeParticipant(Organization participant) {
        if (this.participants.remove(participant)) {
            participant.removeTrade(this);
        }
    }
}