package com.revature.accountmanagementbackend.repository;

import java.util.Date;
import java.util.List;

import com.revature.accountmanagementbackend.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

  @Query("SELECT t FROM Transaction t WHERE t.account.accountNumber = ?1")
  List<Transaction> findAllByAccount(long accountNumber);

  @Query("SELECT t FROM Transaction t WHERE t.account.accountNumber = ?1 AND t.dateTime BETWEEN ?2 AND ?3")
  List<Transaction> findAllByDateRange(long accountNumber, Date start, Date end);

}
