package com.revature.accountmanagementbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @Column(unique = true)
  String username;

  @JsonProperty(access = Access.WRITE_ONLY)
  String password;

  @ManyToOne(optional = false)
  @JoinColumn(name = "role_id", nullable = false)
  Role role;

  @OneToOne(mappedBy = "user")
  @JsonIgnoreProperties("user")
  Customer customer;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public User() {
    super();
  }

  public User(String username, String password) {
    super();
    this.username = username;
    this.password = password;
  }

  public User(int id) {
    super();
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + "]";
  }
}
