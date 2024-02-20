package com.trade_platform.Repository.Organization.Reference;

import com.trade_platform.Entity.Organization.Reference.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
}