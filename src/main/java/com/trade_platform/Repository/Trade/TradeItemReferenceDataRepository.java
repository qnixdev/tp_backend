package com.trade_platform.Repository.Trade;

import com.trade_platform.Entity.Trade.TradeItemReferenceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TradeItemReferenceDataRepository extends CrudRepository<TradeItemReferenceData, UUID> {
}