--
-- Name: address; Type: TABLE; Schema: public
--
CREATE TABLE address (
    id UUID NOT NULL,
    id_organization UUID NOT NULL,
    type VARCHAR(63) NOT NULL,
    id_country UUID NOT NULL,
    id_region UUID DEFAULT NULL,
    city VARCHAR(127) DEFAULT NULL,
    district VARCHAR(127) DEFAULT NULL,
    street VARCHAR(127) DEFAULT NULL,
    build VARCHAR(63) DEFAULT NULL,
    section VARCHAR(63) DEFAULT NULL,
    office VARCHAR(63) DEFAULT NULL,
    zip_code VARCHAR(63) DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_D4E6F81E22F160E ON address (id_organization);
CREATE INDEX IDX_D4E6F818DEE6016 ON address (id_country);
CREATE INDEX IDX_D4E6F8198260155 ON address (id_region);
COMMENT ON COLUMN address.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN address.id_organization IS '(DC2Type:uuid)';
COMMENT ON COLUMN address.id_country IS '(DC2Type:uuid)';
COMMENT ON COLUMN address.id_region IS '(DC2Type:uuid)';


--
-- Name: assortment; Type: TABLE; Schema: public
--
CREATE TABLE assortment (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN assortment.id IS '(DC2Type:uuid)';


--
-- Name: bank_info; Type: TABLE; Schema: public
--
CREATE TABLE bank_info (
    id UUID NOT NULL,
    id_organization UUID NOT NULL,
    id_currency UUID NOT NULL,
    mfo VARCHAR(63) DEFAULT NULL,
    bank_name VARCHAR(255) DEFAULT NULL,
    bank_address VARCHAR(255) DEFAULT NULL,
    iban VARCHAR(63) DEFAULT NULL,
    swift_code VARCHAR(63) DEFAULT NULL,
    account_info VARCHAR(511) DEFAULT NULL,
    is_default BOOLEAN NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_updated_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_61E963D9E22F160E ON bank_info (id_organization);
CREATE INDEX IDX_61E963D9398D64AA ON bank_info (id_currency);
COMMENT ON COLUMN bank_info.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN bank_info.id_organization IS '(DC2Type:uuid)';
COMMENT ON COLUMN bank_info.id_currency IS '(DC2Type:uuid)';


--
-- Name: business_form; Type: TABLE; Schema: public
--
CREATE TABLE business_form (
    id UUID NOT NULL,
    type VARCHAR(63) NOT NULL,
    name VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN business_form.id IS '(DC2Type:uuid)';


--
-- Name: category; Type: TABLE; Schema: public
--
CREATE TABLE category (
    id UUID NOT NULL,
    type VARCHAR(63) NOT NULL,
    name VARCHAR(63) NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN category.id IS '(DC2Type:uuid)';


--
-- Name: contact_info; Type: TABLE; Schema: public
--
CREATE TABLE contact_info (
    id UUID NOT NULL,
    id_organization UUID NOT NULL,
    email VARCHAR(63) DEFAULT NULL,
    phone VARCHAR(63) DEFAULT NULL,
    fax VARCHAR(63) DEFAULT NULL,
    web_site VARCHAR(127) DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE UNIQUE INDEX UNIQ_E376B3A8E22F160E ON contact_info (id_organization);
COMMENT ON COLUMN contact_info.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN contact_info.id_organization IS '(DC2Type:uuid)';


--
-- Name: country; Type: TABLE; Schema: public
--
CREATE TABLE country (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    alpha2_code VARCHAR(7) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN country.id IS '(DC2Type:uuid)';


--
-- Name: currency; Type: TABLE; Schema: public
--
CREATE TABLE currency (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    r030 INT NOT NULL,
    cc VARCHAR(7) NOT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX cc_index ON currency (cc);
COMMENT ON COLUMN currency.id IS '(DC2Type:uuid)';


--
-- Name: currency_rate; Type: TABLE; Schema: public
--
CREATE TABLE currency_rate (
    id UUID NOT NULL,
    id_currency UUID NOT NULL,
    rate DOUBLE PRECISION NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_exchange_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_555B7C4D398D64AA ON currency_rate (id_currency);
CREATE INDEX date_exchange_index ON currency_rate (date_exchange_at);
COMMENT ON COLUMN currency_rate.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN currency_rate.id_currency IS '(DC2Type:uuid)';


--
-- Name: customer; Type: TABLE; Schema: public
--
CREATE TABLE customer (
    id UUID NOT NULL,
    id_organization UUID NOT NULL,
    id_created_user UUID DEFAULT NULL,
    status VARCHAR(63) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) DEFAULT NULL,
    api_token VARCHAR(255) DEFAULT NULL,
    is_blocked BOOLEAN NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_updated_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_last_activity_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_deleted_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE UNIQUE INDEX UNIQ_81398E09E7927C74 ON customer (email);
CREATE UNIQUE INDEX UNIQ_81398E097BA2F5EB ON customer (api_token);
CREATE INDEX IDX_81398E09E22F160E ON customer (id_organization);
CREATE INDEX IDX_81398E09A9255C2F ON customer (id_created_user);
COMMENT ON COLUMN customer.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN customer.id_organization IS '(DC2Type:uuid)';
COMMENT ON COLUMN customer.id_created_user IS '(DC2Type:uuid)';


--
-- Name: customers_security_groups; Type: TABLE; Schema: public
--
CREATE TABLE customers_security_groups (
    id_customer UUID NOT NULL,
    id_security_group UUID NOT NULL,
    PRIMARY KEY(id_customer, id_security_group)
);
CREATE INDEX IDX_1043203ED1E2629C ON customers_security_groups (id_customer);
CREATE INDEX IDX_1043203EBC1A1A69 ON customers_security_groups (id_security_group);
COMMENT ON COLUMN customers_security_groups.id_customer IS '(DC2Type:uuid)';
COMMENT ON COLUMN customers_security_groups.id_security_group IS '(DC2Type:uuid)';


--
-- Name: delivery_terms; Type: TABLE; Schema: public
--
CREATE TABLE delivery_terms (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN delivery_terms.id IS '(DC2Type:uuid)';


--
-- Name: diameter; Type: TABLE; Schema: public
--
CREATE TABLE diameter (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN diameter.id IS '(DC2Type:uuid)';


--
-- Name: employee; Type: TABLE; Schema: public
--
CREATE TABLE employee (
    id UUID NOT NULL,
    type VARCHAR(63) NOT NULL,
    full_name VARCHAR(127) NOT NULL,
    position VARCHAR(127) DEFAULT NULL,
    email VARCHAR(63) DEFAULT NULL,
    phone VARCHAR(63) DEFAULT NULL,
    date_expired_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN employee.id IS '(DC2Type:uuid)';


--
-- Name: height; Type: TABLE; Schema: public
--
CREATE TABLE height (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN height.id IS '(DC2Type:uuid)';


--
-- Name: humidity; Type: TABLE; Schema: public
--
CREATE TABLE humidity (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN humidity.id IS '(DC2Type:uuid)';


--
-- Name: length; Type: TABLE; Schema: public
--
CREATE TABLE length (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN length.id IS '(DC2Type:uuid)';


--
-- Name: measure; Type: TABLE; Schema: public
--
CREATE TABLE measure (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN measure.id IS '(DC2Type:uuid)';


--
-- Name: organization; Type: TABLE; Schema: public
--
CREATE TABLE organization (
    id UUID NOT NULL,
    id_created_user UUID DEFAULT NULL,
    status VARCHAR(63) NOT NULL,
    name VARCHAR(255) NOT NULL,
    usreou VARCHAR(63) NOT NULL,
    is_resident BOOLEAN NOT NULL,
    id_business_form UUID DEFAULT NULL,
    id_ownership UUID DEFAULT NULL,
    id_taxation_type UUID DEFAULT NULL,
    pdv VARCHAR(63) DEFAULT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_updated_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_deleted_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_registration_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    affiliate_number VARCHAR(63) DEFAULT NULL,
    is_broker BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_C1EE637CA9255C2F ON organization (id_created_user);
CREATE INDEX IDX_C1EE637CBC3B2E27 ON organization (id_business_form);
CREATE INDEX IDX_C1EE637C98A550B3 ON organization (id_ownership);
CREATE INDEX IDX_C1EE637C3810D62A ON organization (id_taxation_type);
COMMENT ON COLUMN organization.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN organization.id_created_user IS '(DC2Type:uuid)';
COMMENT ON COLUMN organization.id_business_form IS '(DC2Type:uuid)';
COMMENT ON COLUMN organization.id_ownership IS '(DC2Type:uuid)';
COMMENT ON COLUMN organization.id_taxation_type IS '(DC2Type:uuid)';


--
-- Name: organizations_categories; Type: TABLE; Schema: public
--
CREATE TABLE organizations_categories (
    id_organization UUID NOT NULL,
    id_category UUID NOT NULL,
    PRIMARY KEY(id_organization, id_category)
);
CREATE INDEX IDX_74DF8412E22F160E ON organizations_categories (id_organization);
CREATE INDEX IDX_74DF8412D449934 ON organizations_categories (id_category);
COMMENT ON COLUMN organizations_categories.id_organization IS '(DC2Type:uuid)';
COMMENT ON COLUMN organizations_categories.id_category IS '(DC2Type:uuid)';


--
-- Name: organizations_employees; Type: TABLE; Schema: public
--
CREATE TABLE organizations_employees (
    id_organization UUID NOT NULL,
    id_employee UUID NOT NULL,
    PRIMARY KEY(id_organization, id_employee)
);
CREATE INDEX IDX_92C3EDE2E22F160E ON organizations_employees (id_organization);
CREATE INDEX IDX_92C3EDE2D449934 ON organizations_employees (id_employee);
COMMENT ON COLUMN organizations_employees.id_organization IS '(DC2Type:uuid)';
COMMENT ON COLUMN organizations_employees.id_employee IS '(DC2Type:uuid)';


--
-- Name: ownership; Type: TABLE; Schema: public
--
CREATE TABLE ownership (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN ownership.id IS '(DC2Type:uuid)';


--
-- Name: phone; Type: TABLE; Schema: public
--
CREATE TABLE phone (
    id UUID NOT NULL,
    id_customer UUID NOT NULL,
    phone VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_444F97DDD1E2629C ON phone (id_customer);
COMMENT ON COLUMN phone.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN phone.id_customer IS '(DC2Type:uuid)';


--
-- Name: quality_class; Type: TABLE; Schema: public
--
CREATE TABLE quality_class (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN quality_class.id IS '(DC2Type:uuid)';


--
-- Name: region; Type: TABLE; Schema: public
--
CREATE TABLE region (
    id UUID NOT NULL,
    id_country UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_F62F1768DEE6016 ON region (id_country);
COMMENT ON COLUMN region.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN region.id_country IS '(DC2Type:uuid)';


--
-- Name: security_group; Type: TABLE; Schema: public
--
CREATE TABLE security_group (
    id UUID NOT NULL,
    "group" VARCHAR(63) NOT NULL,
    name VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN security_group.id IS '(DC2Type:uuid)';


--
-- Name: groups_roles; Type: TABLE; Schema: public
--
CREATE TABLE groups_roles (
    id_group UUID NOT NULL,
    id_role UUID NOT NULL,
    PRIMARY KEY(id_group, id_role)
);
CREATE INDEX IDX_E79D4963834505F5 ON groups_roles (id_group);
CREATE INDEX IDX_E79D4963DC499668 ON groups_roles (id_role);
COMMENT ON COLUMN groups_roles.id_group IS '(DC2Type:uuid)';
COMMENT ON COLUMN groups_roles.id_role IS '(DC2Type:uuid)';


--
-- Name: security_role; Type: TABLE; Schema: public
--
CREATE TABLE security_role (
    id UUID NOT NULL,
    role VARCHAR(63) NOT NULL,
    slug VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN security_role.id IS '(DC2Type:uuid)';


--
-- Name: species; Type: TABLE; Schema: public
--
CREATE TABLE species (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN species.id IS '(DC2Type:uuid)';


--
-- Name: taxation_type; Type: TABLE; Schema: public
--
CREATE TABLE taxation_type (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN taxation_type.id IS '(DC2Type:uuid)';


--
-- Name: trade; Type: TABLE; Schema: public
--
CREATE TABLE trade (
    id UUID NOT NULL,
    id_created_user UUID DEFAULT NULL,
    id_created_customer UUID DEFAULT NULL,
    id_created_organization UUID DEFAULT NULL,
    status VARCHAR(63) NOT NULL,
    title VARCHAR(511) NOT NULL,
    id_category UUID NOT NULL,
    id_trade_type UUID NOT NULL,
    id_trade_model UUID NOT NULL,
    id_trade_specification UUID NOT NULL,
    step DOUBLE PRECISION NOT NULL,
    quarter SMALLINT NOT NULL,
    date_offer_start_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_offer_end_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_trade_start_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_trade_end_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_paused_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    limit_plus SMALLINT DEFAULT NULL,
    commission DOUBLE PRECISION DEFAULT NULL,
    commission_agent DOUBLE PRECISION DEFAULT NULL,
    is_auto_change_status BOOLEAN NOT NULL,
    id_region UUID DEFAULT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_updated_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_deleted_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_7E1A4366A9255C2F ON trade (id_created_user);
CREATE INDEX IDX_7E1A4366FD4A556F ON trade (id_created_customer);
CREATE INDEX IDX_7E1A43663DE0650 ON trade (id_created_organization);
CREATE INDEX IDX_7E1A43665697F554 ON trade (id_category);
CREATE INDEX IDX_7E1A43664BAFA005 ON trade (id_trade_type);
CREATE INDEX IDX_7E1A4366E58A6FCD ON trade (id_trade_model);
CREATE INDEX IDX_7E1A4366D9A683ED ON trade (id_trade_specification);
CREATE INDEX IDX_7E1A43662955449B ON trade (id_region);
COMMENT ON COLUMN trade.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade.id_created_user IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade.id_created_customer IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade.id_created_organization IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade.id_category IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade.id_trade_type IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade.id_trade_model IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade.id_trade_specification IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade.id_region IS '(DC2Type:uuid)';


--
-- Name: trades_participants; Type: TABLE; Schema: public
--
CREATE TABLE trades_participants (
    id_trade UUID NOT NULL,
    id_participant UUID NOT NULL,
    PRIMARY KEY(id_trade, id_participant)
);
CREATE INDEX IDX_5DD0FE72909F0256 ON trades_participants (id_trade);
CREATE INDEX IDX_5DD0FE72CF8DA6E6 ON trades_participants (id_participant);
COMMENT ON COLUMN trades_participants.id_trade IS '(DC2Type:uuid)';
COMMENT ON COLUMN trades_participants.id_participant IS '(DC2Type:uuid)';


--
-- Name: trade_item_reference_data; Type: TABLE; Schema: public
--
CREATE TABLE trade_item_reference_data (
    id UUID NOT NULL,
    id_trade UUID NOT NULL,
    id_lot UUID DEFAULT NULL,
    id_position UUID DEFAULT NULL,
    id_measure UUID NOT NULL,
    id_species UUID DEFAULT NULL,
    id_assortment UUID DEFAULT NULL,
    id_quality_class UUID DEFAULT NULL,
    id_warehouse UUID DEFAULT NULL,
    id_diameter UUID DEFAULT NULL,
    id_length UUID DEFAULT NULL,
    id_width UUID DEFAULT NULL,
    id_height UUID DEFAULT NULL,
    id_humidity UUID DEFAULT NULL,
    id_delivery_terms UUID DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_EB6E2DFC909F0256 ON trade_item_reference_data (id_trade);
CREATE UNIQUE INDEX UNIQ_EB6E2DFC9525C141 ON trade_item_reference_data (id_lot);
CREATE UNIQUE INDEX UNIQ_EB6E2DFC16F70860 ON trade_item_reference_data (id_position);
CREATE INDEX IDX_EB6E2DFC5E9AB055 ON trade_item_reference_data (id_measure);
CREATE INDEX IDX_EB6E2DFC7B925E62 ON trade_item_reference_data (id_species);
CREATE INDEX IDX_EB6E2DFC8FD4ED5C ON trade_item_reference_data (id_assortment);
CREATE INDEX IDX_EB6E2DFC47CD00C9 ON trade_item_reference_data (id_quality_class);
CREATE INDEX IDX_EB6E2DFC6C8637DB ON trade_item_reference_data (id_warehouse);
CREATE INDEX IDX_EB6E2DFC24FDB3C7 ON trade_item_reference_data (id_diameter);
CREATE INDEX IDX_EB6E2DFC274A2B5F ON trade_item_reference_data (id_length);
CREATE INDEX IDX_EB6E2DFC629F041F ON trade_item_reference_data (id_width);
CREATE INDEX IDX_EB6E2DFCD37A50E2 ON trade_item_reference_data (id_height);
CREATE INDEX IDX_EB6E2DFC39279B57 ON trade_item_reference_data (id_humidity);
CREATE INDEX IDX_EB6E2DFCA5CF64E5 ON trade_item_reference_data (id_delivery_terms);
COMMENT ON COLUMN trade_item_reference_data.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_trade IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_lot IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_position IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_measure IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_species IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_assortment IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_quality_class IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_warehouse IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_diameter IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_length IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_width IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_height IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_humidity IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_item_reference_data.id_delivery_terms IS '(DC2Type:uuid)';


--
-- Name: trade_lot; Type: TABLE; Schema: public
--
CREATE TABLE trade_lot (
    id UUID NOT NULL,
    id_trade UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    clarification VARCHAR(511) DEFAULT NULL,
    quantity INT DEFAULT NULL,
    number INT NOT NULL,
    number_position INT NOT NULL,
    is_blocked BOOLEAN NOT NULL,
    volume DOUBLE PRECISION NOT NULL,
    price DOUBLE PRECISION DEFAULT NULL,
    cost DOUBLE PRECISION NOT NULL,
    owner VARCHAR(255) NOT NULL,
    id_best_bet UUID DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_2DEB0D47909F0256 ON trade_lot (id_trade);
CREATE UNIQUE INDEX UNIQ_2DEB0D476882E465 ON trade_lot (id_best_bet);
COMMENT ON COLUMN trade_lot.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_lot.id_trade IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_lot.id_best_bet IS '(DC2Type:uuid)';


--
-- Name: trade_lot_bet; Type: TABLE; Schema: public
--
CREATE TABLE trade_lot_bet (
    id UUID NOT NULL,
    id_trade UUID NOT NULL,
    id_lot UUID NOT NULL,
    id_participant UUID NOT NULL,
    id_customer UUID NOT NULL,
    bet DOUBLE PRECISION NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_finished_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_check BIGINT DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_40D570E4909F0256 ON trade_lot_bet (id_trade);
CREATE INDEX IDX_40D570E49525C141 ON trade_lot_bet (id_lot);
CREATE INDEX IDX_40D570E4CF8DA6E6 ON trade_lot_bet (id_participant);
CREATE INDEX IDX_40D570E4D1E2629C ON trade_lot_bet (id_customer);
CREATE UNIQUE INDEX lot_bet_index ON trade_lot_bet (id_lot, bet);
COMMENT ON COLUMN trade_lot_bet.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_lot_bet.id_trade IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_lot_bet.id_lot IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_lot_bet.id_participant IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_lot_bet.id_customer IS '(DC2Type:uuid)';


--
-- Name: trade_lot_position; Type: TABLE; Schema: public
--
CREATE TABLE trade_lot_position (
    id UUID NOT NULL,
    id_lot UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    clarification VARCHAR(511) NOT NULL,
    quantity INT NOT NULL,
    number INT NOT NULL,
    number_position INT NOT NULL,
    volume DOUBLE PRECISION NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    cost DOUBLE PRECISION NOT NULL,
    owner VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_F38F3E8F9525C141 ON trade_lot_position (id_lot);
COMMENT ON COLUMN trade_lot_position.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_lot_position.id_lot IS '(DC2Type:uuid)';


--
-- Name: trade_model; Type: TABLE; Schema: public
--
CREATE TABLE trade_model (
    id UUID NOT NULL,
    slug VARCHAR(63) NOT NULL,
    name VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN trade_model.id IS '(DC2Type:uuid)';


--
-- Name: trade_request; Type: TABLE; Schema: public
--
CREATE TABLE trade_request (
    id UUID NOT NULL,
    id_trade UUID NOT NULL,
    id_lot UUID NOT NULL,
    id_participant UUID NOT NULL,
    id_customer UUID NOT NULL,
    status VARCHAR(63) NOT NULL,
    is_admitted_to_trading BOOLEAN NOT NULL,
    deposit_expected DOUBLE PRECISION NOT NULL,
    deposit_actual DOUBLE PRECISION NOT NULL,
    date_signed_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_C817D769909F0256 ON trade_request (id_trade);
CREATE INDEX IDX_C817D7699525C141 ON trade_request (id_lot);
CREATE INDEX IDX_C817D769CF8DA6E6 ON trade_request (id_participant);
CREATE INDEX IDX_C817D769D1E2629C ON trade_request (id_customer);
CREATE INDEX status_index ON trade_request (status);
CREATE UNIQUE INDEX participant_lot_index ON trade_request (id_participant, id_lot);
COMMENT ON COLUMN trade_request.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_request.id_trade IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_request.id_lot IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_request.id_participant IS '(DC2Type:uuid)';
COMMENT ON COLUMN trade_request.id_customer IS '(DC2Type:uuid)';


--
-- Name: trade_specification; Type: TABLE; Schema: public
--
CREATE TABLE trade_specification (
    id UUID NOT NULL,
    slug VARCHAR(63) NOT NULL,
    name VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN trade_specification.id IS '(DC2Type:uuid)';


--
-- Name: trade_type; Type: TABLE; Schema: public
--
CREATE TABLE trade_type (
    id UUID NOT NULL,
    slug VARCHAR(63) NOT NULL,
    name VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN trade_type.id IS '(DC2Type:uuid)';


--
-- Name: user; Type: TABLE; Schema: public
--
CREATE TABLE "user" (
    id UUID NOT NULL,
    id_created_user UUID DEFAULT NULL,
    status VARCHAR(63) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) DEFAULT NULL,
    api_token VARCHAR(255) DEFAULT NULL,
    is_blocked BOOLEAN NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    date_updated_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_last_activity_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    date_deleted_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE UNIQUE INDEX UNIQ_8D93D649E7927C74 ON "user" (email);
CREATE UNIQUE INDEX UNIQ_8D93D6497BA2F5EB ON "user" (api_token);
CREATE INDEX IDX_8D93D649E104C1D3 ON "user" (id_created_user);
COMMENT ON COLUMN "user".id IS '(DC2Type:uuid)';
COMMENT ON COLUMN "user".id_created_user IS '(DC2Type:uuid)';


--
-- Name: users_security_groups; Type: TABLE; Schema: public
--
CREATE TABLE users_security_groups (
    id_user UUID NOT NULL,
    id_security_group UUID NOT NULL,
    PRIMARY KEY(id_user, id_security_group)
);
CREATE INDEX IDX_7DF730FF6B3CA4B ON users_security_groups (id_user);
CREATE INDEX IDX_7DF730FFBC1A1A69 ON users_security_groups (id_security_group);
COMMENT ON COLUMN users_security_groups.id_user IS '(DC2Type:uuid)';
COMMENT ON COLUMN users_security_groups.id_security_group IS '(DC2Type:uuid)';


--
-- Name: warehouse; Type: TABLE; Schema: public
--
CREATE TABLE warehouse (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN warehouse.id IS '(DC2Type:uuid)';


--
-- Name: width; Type: TABLE; Schema: public
--
CREATE TABLE width (
    id UUID NOT NULL,
    name VARCHAR(63) NOT NULL,
    type VARCHAR(63) NOT NULL,
    is_approved BOOLEAN NOT NULL,
    is_show_in_catalog BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN width.id IS '(DC2Type:uuid)';


ALTER TABLE address ADD CONSTRAINT FK_D4E6F81E22F160E FOREIGN KEY (id_organization) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE address ADD CONSTRAINT FK_D4E6F818DEE6016 FOREIGN KEY (id_country) REFERENCES country (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE address ADD CONSTRAINT FK_D4E6F8198260155 FOREIGN KEY (id_region) REFERENCES region (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bank_info ADD CONSTRAINT FK_61E963D9E22F160E FOREIGN KEY (id_organization) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bank_info ADD CONSTRAINT FK_61E963D9398D64AA FOREIGN KEY (id_currency) REFERENCES currency (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE contact_info ADD CONSTRAINT FK_E376B3A8E22F160E FOREIGN KEY (id_organization) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE currency_rate ADD CONSTRAINT FK_555B7C4D398D64AA FOREIGN KEY (id_currency) REFERENCES currency (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE customer ADD CONSTRAINT FK_81398E09E22F160E FOREIGN KEY (id_organization) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE customer ADD CONSTRAINT FK_81398E09A9255C2F FOREIGN KEY (id_created_user) REFERENCES "user" (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE customers_security_groups ADD CONSTRAINT FK_1043203ED1E2629C FOREIGN KEY (id_customer) REFERENCES customer (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE customers_security_groups ADD CONSTRAINT FK_1043203EBC1A1A69 FOREIGN KEY (id_security_group) REFERENCES security_group (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE organization ADD CONSTRAINT FK_C1EE637CA9255C2F FOREIGN KEY (id_created_user) REFERENCES "user" (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE organization ADD CONSTRAINT FK_C1EE637CBC3B2E27 FOREIGN KEY (id_business_form) REFERENCES business_form (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE organization ADD CONSTRAINT FK_C1EE637C98A550B3 FOREIGN KEY (id_ownership) REFERENCES ownership (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE organization ADD CONSTRAINT FK_C1EE637C3810D62A FOREIGN KEY (id_taxation_type) REFERENCES taxation_type (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE organizations_categories ADD CONSTRAINT FK_74DF8412E22F160E FOREIGN KEY (id_organization) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE organizations_categories ADD CONSTRAINT FK_74DF8412D449934 FOREIGN KEY (id_category) REFERENCES category (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE organizations_employees ADD CONSTRAINT FK_92C3EDE2E22F160E FOREIGN KEY (id_organization) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE organizations_employees ADD CONSTRAINT FK_92C3EDE2D449934 FOREIGN KEY (id_employee) REFERENCES employee (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE phone ADD CONSTRAINT FK_444F97DDD1E2629C FOREIGN KEY (id_customer) REFERENCES customer (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE region ADD CONSTRAINT FK_F62F1768DEE6016 FOREIGN KEY (id_country) REFERENCES country (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE groups_roles ADD CONSTRAINT FK_E79D4963834505F5 FOREIGN KEY (id_group) REFERENCES security_group (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE groups_roles ADD CONSTRAINT FK_E79D4963DC499668 FOREIGN KEY (id_role) REFERENCES security_role (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade ADD CONSTRAINT FK_7E1A4366A9255C2F FOREIGN KEY (id_created_user) REFERENCES "user" (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade ADD CONSTRAINT FK_7E1A4366FD4A556F FOREIGN KEY (id_created_customer) REFERENCES customer (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade ADD CONSTRAINT FK_7E1A43663DE0650 FOREIGN KEY (id_created_organization) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade ADD CONSTRAINT FK_7E1A43665697F554 FOREIGN KEY (id_category) REFERENCES category (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade ADD CONSTRAINT FK_7E1A43664BAFA005 FOREIGN KEY (id_trade_type) REFERENCES trade_type (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade ADD CONSTRAINT FK_7E1A4366E58A6FCD FOREIGN KEY (id_trade_model) REFERENCES trade_model (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade ADD CONSTRAINT FK_7E1A4366D9A683ED FOREIGN KEY (id_trade_specification) REFERENCES trade_specification (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade ADD CONSTRAINT FK_7E1A43662955449B FOREIGN KEY (id_region) REFERENCES region (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trades_participants ADD CONSTRAINT FK_5DD0FE72909F0256 FOREIGN KEY (id_trade) REFERENCES trade (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trades_participants ADD CONSTRAINT FK_5DD0FE72CF8DA6E6 FOREIGN KEY (id_participant) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC909F0256 FOREIGN KEY (id_trade) REFERENCES trade (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC9525C141 FOREIGN KEY (id_lot) REFERENCES trade_lot (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC16F70860 FOREIGN KEY (id_position) REFERENCES trade_lot_position (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC5E9AB055 FOREIGN KEY (id_measure) REFERENCES measure (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC7B925E62 FOREIGN KEY (id_species) REFERENCES species (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC8FD4ED5C FOREIGN KEY (id_assortment) REFERENCES assortment (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC47CD00C9 FOREIGN KEY (id_quality_class) REFERENCES quality_class (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC6C8637DB FOREIGN KEY (id_warehouse) REFERENCES warehouse (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC24FDB3C7 FOREIGN KEY (id_diameter) REFERENCES diameter (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC274A2B5F FOREIGN KEY (id_length) REFERENCES length (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC629F041F FOREIGN KEY (id_width) REFERENCES width (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFCD37A50E2 FOREIGN KEY (id_height) REFERENCES height (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFC39279B57 FOREIGN KEY (id_humidity) REFERENCES humidity (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_item_reference_data ADD CONSTRAINT FK_EB6E2DFCA5CF64E5 FOREIGN KEY (id_delivery_terms) REFERENCES delivery_terms (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_lot ADD CONSTRAINT FK_2DEB0D47909F0256 FOREIGN KEY (id_trade) REFERENCES trade (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_lot ADD CONSTRAINT FK_2DEB0D476882E465 FOREIGN KEY (id_best_bet) REFERENCES trade_lot_bet (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_lot_bet ADD CONSTRAINT FK_40D570E4909F0256 FOREIGN KEY (id_trade) REFERENCES trade (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_lot_bet ADD CONSTRAINT FK_40D570E49525C141 FOREIGN KEY (id_lot) REFERENCES trade_lot (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_lot_bet ADD CONSTRAINT FK_40D570E4CF8DA6E6 FOREIGN KEY (id_participant) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_lot_bet ADD CONSTRAINT FK_40D570E4D1E2629C FOREIGN KEY (id_customer) REFERENCES customer (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_lot_position ADD CONSTRAINT FK_F38F3E8F9525C141 FOREIGN KEY (id_lot) REFERENCES trade_lot (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_request ADD CONSTRAINT FK_C817D769909F0256 FOREIGN KEY (id_trade) REFERENCES trade (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_request ADD CONSTRAINT FK_C817D7699525C141 FOREIGN KEY (id_lot) REFERENCES trade_lot (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_request ADD CONSTRAINT FK_C817D769CF8DA6E6 FOREIGN KEY (id_participant) REFERENCES organization (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trade_request ADD CONSTRAINT FK_C817D769D1E2629C FOREIGN KEY (id_customer) REFERENCES customer (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "user" ADD CONSTRAINT FK_8D93D649E104C1D3 FOREIGN KEY (id_created_user) REFERENCES "user" (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE users_security_groups ADD CONSTRAINT FK_7DF730FF6B3CA4B FOREIGN KEY (id_user) REFERENCES "user" (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE users_security_groups ADD CONSTRAINT FK_7DF730FFBC1A1A69 FOREIGN KEY (id_security_group) REFERENCES security_group (id) NOT DEFERRABLE INITIALLY IMMEDIATE;