package com.trade_platform.Repository;

import com.trade_platform.Entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, UUID> {
}