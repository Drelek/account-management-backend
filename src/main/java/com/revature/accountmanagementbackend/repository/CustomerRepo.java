package com.revature.accountmanagementbackend.repository;

import java.util.Optional;

import com.revature.accountmanagementbackend.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

  @Query("SELECT c FROM Customer c WHERE c.user.id = ?1")
  Optional<Customer> findByUser(int id);

  @Query("SELECT c FROM Customer c WHERE c.user.username = ?1")
  Optional<Customer> findByUser(String username);

}
