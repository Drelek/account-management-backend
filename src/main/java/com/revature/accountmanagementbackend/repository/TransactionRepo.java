package com.revature.accountmanagementbackend.repository;

import com.revature.accountmanagementbackend.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

}
