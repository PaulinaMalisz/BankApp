package com.bart.bank.customer;

import com.bart.bank.domain.CustomerEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
  Optional<CustomerEntity> findByLoginUserName(String userName);
}
