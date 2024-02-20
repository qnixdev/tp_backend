package com.trade_platform.Repository.Organization.Reference;

import com.trade_platform.Entity.Organization.Reference.ContactInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ContactInfoRepository extends CrudRepository<ContactInfo, UUID> {
}