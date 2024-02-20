package com.trade_platform.Repository;

import com.trade_platform.Entity.CurrencyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, UUID> {
}