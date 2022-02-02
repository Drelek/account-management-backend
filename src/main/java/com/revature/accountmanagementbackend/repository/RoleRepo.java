package com.revature.accountmanagementbackend.repository;

import java.util.Optional;

import com.revature.accountmanagementbackend.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

  @Query("SELECT r FROM Role r WHERE r.name = ?1")
  Optional<Role> findByName(String name);

}
