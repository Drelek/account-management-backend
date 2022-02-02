package com.revature.accountmanagementbackend.repository;

import com.revature.accountmanagementbackend.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
