package com.revature.accountmanagementbackend.repository;

import com.revature.accountmanagementbackend.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

}
