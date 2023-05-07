package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import java.util.List;

public interface IMetroCardService {
  // public void balance(String cardNum, Integer balanceAvailable) throws CardNumberNotFoundException;
  //public void printSummary();
  /*
        Adds new metro card from input, maintains a map of card_id mapped to card
        @param cardId - card number
        @param balance - balance amount in card
     */
  void addCard(String cardId, int balance);

  /*
        Return list of all metro cards
        @return List - of metro cards
     */
  List<MetroCard> getCards();

  /*
        Performs a transaction on metro card from input
        @param cardId - card number
        @param amount - transaction amount
        @return int - amount left to pay
     */
  int transactCard(String cardId, int amount);
}
