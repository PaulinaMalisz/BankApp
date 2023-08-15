package com.bart.bank.account;

import com.bart.bank.domain.AccountEntity;
import com.bart.bank.domain.Status;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

  <T> T getById(Long id, Class<T> type);

  List<ConfirmAccount> findByStatusIn(List<Status> status);

  Optional<AccountEntity> findByCustomerLoginUserName(String userName);

  Optional<AccountEntity> findByNumber(String number);
}
