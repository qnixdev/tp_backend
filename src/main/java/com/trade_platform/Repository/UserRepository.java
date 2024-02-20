package com.trade_platform.Repository;

import com.trade_platform.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
}