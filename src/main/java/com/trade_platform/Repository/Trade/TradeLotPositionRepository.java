package com.trade_platform.Repository.Trade;

import com.trade_platform.Entity.Trade.TradeLotPosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TradeLotPositionRepository extends CrudRepository<TradeLotPosition, UUID> {
}