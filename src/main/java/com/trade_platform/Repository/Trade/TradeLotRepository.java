package com.trade_platform.Repository.Trade;

import com.trade_platform.Entity.Trade.TradeLot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TradeLotRepository extends CrudRepository<TradeLot, UUID> {
}