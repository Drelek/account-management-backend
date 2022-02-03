package com.revature.accountmanagementbackend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int id;
  @Column(unique = true)
  String name;

  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
  @JsonIgnore
  List<User> users;

  public Role() {
    super();
  }

  public Role(int id) {
    super();
    this.id = id;
  }

  public Role(String name) {
    super();
    this.name = name;
  }

  public Role(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Role [id=" + id + ", name=" + name + "]";
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
