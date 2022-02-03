package com.revature.accountmanagementbackend.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Customer {
  @Id
  long PAN;
  String proofOfPAN;
  String citizenUID;
  String proofOfUID;
  String name;
  String address;
  String email;
  Date dateOfBirth;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  List<Account> accounts;

  @OneToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "user_id")
  @JsonIgnoreProperties("customer")
  User user;

  public Customer(long pAN, String proofOfPAN, String citizenUID, String proofOfUID, String name, String address,
      String email, Date dateOfBirth, List<Account> accounts, User user) {
    PAN = pAN;
    this.proofOfPAN = proofOfPAN;
    this.citizenUID = citizenUID;
    this.proofOfUID = proofOfUID;
    this.name = name;
    this.address = address;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.accounts = accounts;
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Customer() {
    super();
  }

  public Customer(long PAN, String citizenUID, String name, String address, String email, Date dateOfBirth) {
    super();
    this.PAN = PAN;
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

  public String getProofOfPAN() {
    return proofOfPAN;
  }

  public void setProofOfPAN(String proofOfPAN) {
    this.proofOfPAN = proofOfPAN;
  }

  public String getProofOfUID() {
    return proofOfUID;
  }

  public void setProofOfUID(String proofOfUID) {
    this.proofOfUID = proofOfUID;
  }
}
