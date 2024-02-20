package com.trade_platform.Repository.Organization;

import com.trade_platform.Entity.Organization.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, UUID> {
}