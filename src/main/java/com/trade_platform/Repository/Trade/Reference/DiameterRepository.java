package com.trade_platform.Repository.Trade.Reference;

import com.trade_platform.Entity.Trade.Reference.Diameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface DiameterRepository extends CrudRepository<Diameter, UUID> {
}