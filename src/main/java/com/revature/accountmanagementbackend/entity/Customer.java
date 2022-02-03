package com.revature.accountmanagementbackend.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
  @Id
  long PAN;
  String citizenUID;
  String name;
  String address;
  String email;
  Date dateOfBirth;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  List<Account> accounts;

  public Customer() {
    super();
  }

  public Customer(long pAN, String citizenUID, String name, String address, String email, Date dateOfBirth) {
    super();
    PAN = pAN;
    this.citizenUID = citizenUID;
    this.name = name;
    this.address = address;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
  }

  public long getPAN() {
    return PAN;
  }

  public void setPAN(long pAN) {
    PAN = pAN;
  }

  public String getCitizenUID() {
    return citizenUID;
  }

  public void setCitizenUID(String citizenUID) {
    this.citizenUID = citizenUID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }
}
