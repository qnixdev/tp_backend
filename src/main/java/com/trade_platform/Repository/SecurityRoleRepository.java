package com.trade_platform.Repository;

import com.trade_platform.Entity.SecurityRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SecurityRoleRepository extends CrudRepository<SecurityRole, UUID> {
}