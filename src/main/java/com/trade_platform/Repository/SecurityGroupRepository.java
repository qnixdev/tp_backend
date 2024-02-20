package com.trade_platform.Repository;

import com.trade_platform.Entity.SecurityGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SecurityGroupRepository extends CrudRepository<SecurityGroup, UUID> {
}