package com.geektrust.backend.entities;

public class MetroCard {

  // private final String cardNumber;
  // private Integer cardBalance;
  // private String passengerId;

  // public MetroCard(String cardNumber, Integer cardBalance,String passengerId) {
  //     this.cardNumber = cardNumber;
  //     this.cardBalance = cardBalance;
  //     this.passengerId = passengerId;
  // }

  // public MetroCard(String cardNumber, Integer cardBalance) {
  //     this.cardNumber = cardNumber;
  //     this.cardBalance = cardBalance;
  // }

  // public String getCardNumber() {
  //     return cardNumber;
  // }
  // public String getPassengerId() {
  //     return passengerId;
  // }
  // public Integer getCardBalance() {
  //     return cardBalance;
  // }

  private final String cardId;
  private int balance;

  public MetroCard(String cardId, int balance) {
    this.cardId = cardId;
    this.balance = balance;
  }

  public String getCardId() {
    return this.cardId;
  }

  public int getBalance() {
    return this.balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}
