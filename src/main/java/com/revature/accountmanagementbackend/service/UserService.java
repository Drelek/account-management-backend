package com.revature.accountmanagementbackend.service;

import java.util.List;
import java.util.Optional;

import com.revature.accountmanagementbackend.entity.User;
import com.revature.accountmanagementbackend.exception.EntityAlreadyExistsException;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  UserRepo userRepo;
  RoleService roleService;

  @Autowired
  public UserService(UserRepo userRepo, RoleService roleService) {
    this.userRepo = userRepo;
    this.roleService = roleService;
  }

  /**
   * Create a user in the database
   * 
   * @param user
   * @return
   * @throws EntityAlreadyExistsException
   */
  public User create(User user) throws EntityAlreadyExistsException {
    User existingUser = null;
    try {
      existingUser = read(user.getUsername());
    } catch (InvalidEntityException e) {
    }
    if (existingUser == null)
      throw new EntityAlreadyExistsException("User", "username");
    return userRepo.save(user);
  }

  /**
   * Read list of all users from the database
   * 
   * @return
   */
  public List<User> readAll() {
    return userRepo.findAll();
  }

  /**
   * Read list of all users with a given role
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  public List<User> readAllOfRole(int id) throws InvalidEntityException {
    roleService.read(id);
    return userRepo.findAllByRole(id);
  }

  /**
   * Read list of all users with a given role
   * 
   * @param role
   * @return
   * @throws InvalidEntityException
   */
  public List<User> readAllOfRole(String role) throws InvalidEntityException {
    roleService.read(role);
    return userRepo.findAllByRole(role);
  }

  /**
   * Read user by id
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  public User read(int id) throws InvalidEntityException {
    Optional<User> optionalUser = userRepo.findById(id);
    if (optionalUser.isEmpty())
      throw new InvalidEntityException("User", id);
    return optionalUser.get();
  }

  /**
   * Read user by username
   * 
   * @param username
   * @return
   * @throws InvalidEntityException
   */
  public User read(String username) throws InvalidEntityException {
    Optional<User> optionalUser = userRepo.findByUsername(username);
    if (optionalUser.isEmpty())
      throw new InvalidEntityException("User", username);
    return optionalUser.get();
  }

  /**
   * Update a user in the database
   * 
   * @param user
   * @return
   * @throws InvalidEntityException
   */
  public User update(User user) throws InvalidEntityException {
    Optional<User> optionalUser = userRepo.findById(user.getId());
    if (optionalUser.isEmpty())
      throw new InvalidEntityException("User", user.getId());
    return userRepo.save(user);
  }

  /**
   * Delete a user from the database
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  public User delete(int id) throws InvalidEntityException {
    Optional<User> optionalUser = userRepo.findById(id);
    if (optionalUser.isEmpty())
      throw new InvalidEntityException("User", id);
    User user = optionalUser.get();
    userRepo.deleteById(id);
    return user;
  }

}
