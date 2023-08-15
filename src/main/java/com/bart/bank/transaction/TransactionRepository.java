package com.bart.bank.transaction;

import com.bart.bank.domain.TransactionEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

  <T> T getById(Long id, Class<T> type);

  List<TransactionSummary> findByAccountId(Long id);
}
