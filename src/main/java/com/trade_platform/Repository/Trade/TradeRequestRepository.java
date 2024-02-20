package com.trade_platform.Repository.Trade;

import com.trade_platform.Entity.Trade.TradeRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TradeRequestRepository extends CrudRepository<TradeRequest, UUID> {
}