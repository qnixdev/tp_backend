package com.trade_platform.Repository.Organization.Reference;

import com.trade_platform.Entity.Organization.Reference.TaxationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TaxationTypeRepository extends CrudRepository<TaxationType, UUID> {
}