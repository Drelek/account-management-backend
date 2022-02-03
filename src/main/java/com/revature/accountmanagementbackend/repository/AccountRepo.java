package com.revature.accountmanagementbackend.repository;

import java.util.List;

import com.revature.accountmanagementbackend.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

  @Query("SELECT a FROM Account a WHERE a.owner.PAN = ?1")
  List<Account> findAllByCustomer(long PAN);

  @Query("UPDATE Account a SET a.currentBalance = a.currentBalance + ?2 WHERE a.accountNumber = ?1")
  @Modifying
  void updateBalanceById(long accountNumber, double amount);

}
