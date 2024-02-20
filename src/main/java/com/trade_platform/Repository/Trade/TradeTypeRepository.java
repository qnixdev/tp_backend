package com.trade_platform.Repository.Trade;

import com.trade_platform.Entity.Trade.TradeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TradeTypeRepository extends CrudRepository<TradeType, UUID> {
}