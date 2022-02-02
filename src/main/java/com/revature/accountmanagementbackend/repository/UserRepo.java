package com.revature.accountmanagementbackend.repository;

import java.util.List;
import java.util.Optional;

import com.revature.accountmanagementbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM User u WHERE u.username = ?1")
  Optional<User> findByUsername(String username);

  @Query("SELECT u FROM User u WHERE u.role.id = ?1")
  List<User> findAllByRole(int role_id);

  @Query("SELECT u FROM User u WHERE u.role.name = ?1")
  List<User> findAllByRole(String role_name);

}
