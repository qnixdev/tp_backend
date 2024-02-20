package com.trade_platform.Repository.Trade;

import com.trade_platform.Entity.Trade.Trade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TradeRepository extends CrudRepository<Trade, UUID> {
}