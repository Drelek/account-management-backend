package com.revature.accountmanagementbackend.service;

import java.util.List;
import java.util.Optional;

import com.revature.accountmanagementbackend.entity.Role;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.repository.RoleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  RoleRepo roleRepo;

  @Autowired
  public RoleService(RoleRepo roleRepo) {
    this.roleRepo = roleRepo;
  }

  /**
   * Save a new role to the database
   * 
   * @param role
   * @return
   */
  public Role create(Role role) {
    return roleRepo.save(role);
  }

  /**
   * Read the details of a specific role from the database
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  public Role read(int id) throws InvalidEntityException {
    Optional<Role> optionalRole = roleRepo.findById(id);
    if (optionalRole.isEmpty())
      throw new InvalidEntityException("Role", id);
    return optionalRole.get();
  }

  /**
   * Read the details of a specific role by name
   * 
   * @param name
   * @return
   * @throws InvalidEntityException
   */
  public Role read(String name) throws InvalidEntityException {
    Optional<Role> optionalRole = roleRepo.findByName(name);
    if (optionalRole.isEmpty())
      throw new InvalidEntityException("Role", name);
    return optionalRole.get();
  }

  /**
   * Read all roles from the database
   * 
   * @return
   */
  public List<Role> readAll() {
    return roleRepo.findAll();
  }

  /**
   * Update a role in the database
   * 
   * @param role
   * @return
   * @throws InvalidEntityException
   */
  public Role update(Role role) throws InvalidEntityException {
    Optional<Role> optionalRole = roleRepo.findById(role.getId());
    if (optionalRole.isEmpty())
      throw new InvalidEntityException("Role", role.getId());
    return roleRepo.save(optionalRole.get());
  }

  /**
   * Delete a role from the database
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  public Role delete(int id) throws InvalidEntityException {
    Optional<Role> optionalRole = roleRepo.findById(id);
    if (optionalRole.isEmpty())
      throw new InvalidEntityException("Role", id);
    Role role = optionalRole.get();
    roleRepo.deleteById(id);
    return role;
  }
}
