package com.revature.accountmanagementbackend.config;

import java.util.Arrays;

import com.revature.accountmanagementbackend.entity.Role;
import com.revature.accountmanagementbackend.entity.User;
import com.revature.accountmanagementbackend.repository.RoleRepo;
import com.revature.accountmanagementbackend.repository.UserRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitConfig {
  /**
   * Initialize database with user and manager roles
   * 
   * @param roleRepo
   * @return
   */
  @Bean
  CommandLineRunner commandLineRunner(RoleRepo roleRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
    return args -> {
      Role user = new Role("User");
      Role manager = new Role("Manager");
      User defaultManager = new User("admin", passwordEncoder.encode("Password123"));
      defaultManager.setRole(manager);
      roleRepo.saveAll(Arrays.asList(user, manager));
      userRepo.save(defaultManager);
    };
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
