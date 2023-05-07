package com.geektrust.backend.services;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import com.geektrust.backend.repositories.IMetroCardRepository;
import java.util.List;

public class MetroCardService implements IMetroCardService {

  private final IMetroCardRepository metroCardRepository;

  // private final IPassengerRepository passengerRepository;

  // private Boolean cardFound = false;
  // private List<MetroCard> metroCard;
  // private  int amountCollectedAtAirport = 0;
  // private  int amountCollectedAtCentral = 0;
  // private  int discountGivenAtAirport = 0;
  // private  int discountGivenAtCentral = 0;
  // int adultCentralCount =0 , seniorCentralCount = 0 , kidCentralCount = 0;
  // int adultAirportCount =0 , seniorAirportCount = 0 , kidAirportCount = 0;

  public MetroCardService(IMetroCardRepository metroCardRepository) {
    this.metroCardRepository = metroCardRepository;
  }

  @Override
  public void addCard(String cardId, int balance) {
    MetroCard metroCard = new MetroCard(cardId, balance);
    metroCardRepository.save(metroCard);
  }

  @Override
  public List<MetroCard> getCards() {
    return metroCardRepository.findAll();
  }

  @Override
  public int transactCard(String cardId, int amount) {
    MetroCard metroCard = metroCardRepository.getCard(cardId);
    int balance = metroCard.getBalance(), diff = balance - amount;
    if (diff < Common.ZERO) {
      metroCard.setBalance(Common.ZERO);
      metroCardRepository.save(metroCard);
      return Math.abs(balance - amount);
    }
    metroCard.setBalance(diff);
    metroCardRepository.save(metroCard);
    return Common.ZERO;
  }
  // @Override
  // public void balance(String cardNum, Integer balanceAvailable) throws CardNumberNotFoundException {
  //     // TODO Auto-generated method stub
  //     MetroCard card = new MetroCard(cardNum, balanceAvailable);
  //     metroCardRepository.save(card);
  // }
}
//     @Override
//     public void printSummary(){
//         System.out.println("TOTAL_COLLECTION CENTRAL " + amountCollectedAtCentral + " " + discountGivenAtCentral);
//         System.out.println("PASSENGER_TYPE_SUMMARY");
//         if(adultCentralCount > 0)
//             System.out.println("ADULT "+ adultCentralCount);
//         if(seniorCentralCount > 0)
//             System.out.println("ADULT "+ seniorCentralCount);
//         if(kidCentralCount > 0)
//             System.out.println("ADULT "+ kidCentralCount);
//         System.out.println("TOTAL_COLLECTION AIRPORT " + amountCollectedAtAirport + " " + discountGivenAtAirport);
//         System.out.println("PASSENGER_TYPE_SUMMARY");
//         if(adultAirportCount > 0)
//             System.out.println("ADULT "+ adultAirportCount);
//         if(seniorAirportCount > 0)
//             System.out.println("ADULT "+ seniorAirportCount);
//         if(kidAirportCount > 0)
//             System.out.println("ADULT "+ kidAirportCount);
//     }
// }
