package com.trade_platform.Repository.Organization.Reference;

import com.trade_platform.Entity.Organization.Reference.Ownership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OwnershipRepository extends CrudRepository<Ownership, UUID> {
}