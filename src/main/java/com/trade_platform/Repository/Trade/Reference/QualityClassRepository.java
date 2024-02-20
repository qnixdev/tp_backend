package com.trade_platform.Repository.Trade.Reference;

import com.trade_platform.Entity.Trade.Reference.QualityClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface QualityClassRepository extends CrudRepository<QualityClass, UUID> {
}