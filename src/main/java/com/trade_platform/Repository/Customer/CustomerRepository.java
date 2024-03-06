package com.trade_platform.Repository.Customer;

import com.trade_platform.Entity.Customer.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    @Query("SELECT COUNT(c.id) > 0 FROM Customer c WHERE LOWER(c.email) = :email")
    public boolean isExistCustomerByEmail(@Param("email") String email);

    public Customer getCustomerByEmail(String email);

    public Customer getCustomerByEmailAndPassword(String email, String password);

    public Customer getCustomerByApiToken(String apiToken);
}