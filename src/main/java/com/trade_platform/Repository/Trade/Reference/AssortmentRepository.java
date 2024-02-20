package com.trade_platform.Repository.Trade.Reference;

import com.trade_platform.Entity.Trade.Reference.Assortment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AssortmentRepository extends CrudRepository<Assortment, UUID> {
}