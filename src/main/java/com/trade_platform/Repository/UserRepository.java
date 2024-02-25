package com.trade_platform.Repository;

import com.trade_platform.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    @Query("SELECT COUNT(u.id) > 0 FROM User u WHERE LOWER(u.email) = :email")
    public boolean isExistUserByEmail(@Param("email") String email);

    public User getUserByEmail(String email);
}