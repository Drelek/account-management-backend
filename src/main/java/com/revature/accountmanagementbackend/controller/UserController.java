package com.revature.accountmanagementbackend.controller;

import java.util.List;

import com.revature.accountmanagementbackend.entity.User;
import com.revature.accountmanagementbackend.exception.AuthenticationFailedException;
import com.revature.accountmanagementbackend.exception.EntityAlreadyExistsException;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  UserService userService;
  PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Handler for post requests to the user endpoint
   * 
   * @param user
   * @return
   * @throws EntityAlreadyExistsException
   */
  @PostMapping
  public ResponseEntity<User> create(@RequestBody User user) throws EntityAlreadyExistsException {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return new ResponseEntity<User>(userService.create(user), HttpStatus.CREATED);
  }

  /**
   * Handler for get requests to the user endpoint
   * 
   * @return
   */
  @GetMapping
  public ResponseEntity<List<User>> readAll() {
    return new ResponseEntity<List<User>>(userService.readAll(), HttpStatus.OK);
  }

  /**
   * Handler for get requests for a specific user
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  @GetMapping("/id/{id}")
  public ResponseEntity<User> read(@PathVariable int id) throws InvalidEntityException {
    return new ResponseEntity<User>(userService.read(id), HttpStatus.OK);
  }

  /**
   * Handler for get requests for a specific user by username
   * 
   * @param username
   * @return
   * @throws InvalidEntityException
   */
  @GetMapping("/username/{username}")
  public ResponseEntity<User> read(@PathVariable String username) throws InvalidEntityException {
    return new ResponseEntity<User>(userService.read(username), HttpStatus.OK);
  }

  /**
   * Handler for put requests to the user endpoint
   * 
   * @param user
   * @return
   * @throws InvalidEntityException
   */
  @PutMapping
  public ResponseEntity<User> update(@RequestBody User user) throws InvalidEntityException {
    return new ResponseEntity<User>(userService.update(user), HttpStatus.OK);
  }

  /**
   * Handler for delete requests to the user endpoint
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  @DeleteMapping("/id/{id}")
  public ResponseEntity<User> delete(@PathVariable int id) throws InvalidEntityException {
    return new ResponseEntity<User>(userService.delete(id), HttpStatus.OK);
  }

  /**
   * Handler for authentication requests
   * 
   * @param user
   * @return
   * @throws AuthenticationFailedException
   */
  @PostMapping("/auth")
  public ResponseEntity<Boolean> authenticate(@RequestBody User user) throws AuthenticationFailedException {
    User userFromDb = null;
    try {
      userFromDb = userService.read(user.getUsername());
    } catch (InvalidEntityException e) {
    }
    if (userFromDb == null || passwordEncoder.matches(user.getPassword(), userFromDb.getPassword()))
      throw new AuthenticationFailedException();
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

}
