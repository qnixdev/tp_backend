package com.trade_platform.Repository.Customer.Reference;

import com.trade_platform.Entity.Customer.Reference.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, UUID> {
}