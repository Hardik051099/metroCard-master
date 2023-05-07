package com.geektrust.backend.services;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.dtos.TotalCollection;
import com.geektrust.backend.entities.CheckIn;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;
import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IPassengerRepository;
import com.geektrust.backend.repositories.IRevenueRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PassengerService implements IPassengerService {

  private final IMetroCardRepository metroCardRepository;
  private final IPassengerRepository passengerRepository;
  private IRevenueRepository revenueRepository;
  private final IMetroCardService metroCardService;

  private Boolean cardFound = false;
  private List<MetroCard> metroCard;

  private int amountCollectedAtAirport = 0;
  private int amountCollectedAtCentral = 0;
  private int discountGivenAtAirport = 0;
  private int discountGivenAtCentral = 0;
  int adultCentralCount = 0, seniorCentralCount = 0, kidCentralCount = 0;
  int adultAirportCount = 0, seniorAirportCount = 0, kidAirportCount = 0;

  public PassengerService(
    IMetroCardRepository metroCardRepository,
    IPassengerRepository passengerRepository,
    IRevenueRepository revenueRepository,
    IMetroCardService metroCardService
  ) {
    this.metroCardRepository = metroCardRepository;
    this.passengerRepository = passengerRepository;
    this.revenueRepository = revenueRepository;
    this.metroCardService = metroCardService;
  }

  @Override
  public void checkInPassenger(
    String cardId,
    String passengerType,
    String fromStation
  ) {
    CheckIn checkIn = new CheckIn(cardId, passengerType, fromStation);
    passengerRepository.checkInPassenger(checkIn);
  }

  @Override
  public Map<StationName, Map<PassengerType, Integer>> getStationTypeCountMap() {
    return passengerRepository.getStationTypeCountMap();
  }

  @Override
  public Map<String, Integer> getStationAmountMap() {
    // TODO Auto-generated method stub
    return passengerRepository.getStationAmountMap();
  }

  @Override
  public Map<String, Integer> getStationDiscountMap() {
    // TODO Auto-generated method stub
    return passengerRepository.getStationDiscountMap();
  }
  // The CHECK_IN command should deduct the appropriate amount of travel charge from the MetroCard of the passenger,
  //depending on the passenger type. If the passenger has already made a single journey, then only 50% of the travel
  //charge should be deducted from the MetroCard for their return journey.

  // @Override
  // public void checkIn(String cardNum, String passengerType, String fromStation)
  //         throws CardNumberNotFoundException {
  //     // TODO Auto-generated method stub

  //     int adultFee = 200;
  //     int seniorCitizenFee = 100;
  //     int kidFee = 50;
  //     int fee = 0;
  //     int discount = 0;
  //     int remainingBalance = 0;
  //     List<Integer> fee_disc_list;

  //     if(isCardFound(cardNum)){

  //         Passenger passenger=passengerRepository.findById(metroCard.get(0).getPassengerId()).orElse(null);
  //         if(passenger == null){
  //             throw new CardNumberNotFoundException();
  //         }
  //         MetroCard card = metroCard.get(0);
  //         Integer cardBalance = metroCard.get(0).getCardBalance();
  //         Boolean returnJourneyFlag = passenger.getReturnJourneyFlag();

  //         if(passengerType.equals(PassengerType.ADULT))
  //         {
  //             fee_disc_list = calculateFee(passenger, adultFee);
  //             fee = fee_disc_list.get(0);
  //             discount = fee_disc_list.get(1);
  //             if(fromStation.equalsIgnoreCase("airport")){
  //                 adultAirportCount++;
  //             }
  //             else if(fromStation.equalsIgnoreCase("central")){
  //                 adultCentralCount++;
  //             }

  //         }
  //         else if(passengerType.equals(PassengerType.SENIOR_CITIZEN))
  //         {
  //             fee_disc_list = calculateFee(passenger, adultFee);
  //             fee = fee_disc_list.get(0);
  //             discount = fee_disc_list.get(1);
  //             if(fromStation.equalsIgnoreCase("airport")){
  //                 seniorAirportCount++;
  //             }
  //             else if(fromStation.equalsIgnoreCase("central")){
  //                 seniorCentralCount++;
  //             }
  //         }
  //         else if(passengerType.equals(PassengerType.KID))
  //         {
  //             fee_disc_list = calculateFee(passenger, adultFee);
  //             fee = fee_disc_list.get(0);
  //             discount = fee_disc_list.get(1);
  //             if(fromStation.equalsIgnoreCase("airport")){
  //                 kidAirportCount++;
  //             }
  //             else if(fromStation.equalsIgnoreCase("central")){
  //                 kidCentralCount++;
  //             }
  //         }

  //         if(cardBalance >= fee)
  //         {
  //             remainingBalance = cardBalance - fee;
  //         }
  //         else
  //         {
  //             fee = fee + (int)((0.2)*(fee - cardBalance));
  //             remainingBalance = 0;
  //         }

  //         metroCardRepository.update(cardNum, remainingBalance, passenger.getId());
  //         passengerRepository.update(passenger.getId(), card, passengerType,!returnJourneyFlag);

  //         if(fromStation.equalsIgnoreCase("airport")){
  //             amountCollectedAtAirport += fee;
  //             discountGivenAtAirport += discount;
  //         }
  //         else if(fromStation.equalsIgnoreCase("central")){
  //             amountCollectedAtCentral += fee;
  //             discountGivenAtCentral += discount;
  //         }
  //     }
  //     // if(fromStation.equalsIgnoreCase("airport")){
  //     //     revenueRepository.save(new TotalCollection(StationName.AIRPORT, amountCollectedAtAirport, discountGivenAtAirport, passenger.passengertype, count));
  //     // }
  //     // else if(fromStation.equalsIgnoreCase("central")){
  //     //     revenueRepository.save(new TotalCollection(StationName.CENTRAL, amountCollectedAtCentral, discountGivenAtCentral, passengertype, count));
  //     // }
  //     revenueRepository.save(new TotalCollection(StationName.valueOf(fromStation),(fromStation.equalsIgnoreCase("airport"))?
  //     amountCollectedAtAirport:amountCollectedAtCentral, (fromStation.equalsIgnoreCase("airport"))?
  //     discountGivenAtAirport:discountGivenAtCentral, PassengerType.valueOf(passengerType), 1));
  // }

  // public Boolean isCardFound(String cardNum)
  // {
  //     List<MetroCard> cards = metroCardRepository.findAll();
  //     metroCard = cards.stream().filter(card -> card.getCardNumber().equals(cardNum)).collect(Collectors.toList());

  //     if(metroCard.isEmpty()){
  //         throw new CardNumberNotFoundException("Cannot check balance. Card not found. Please enter valid card number!");
  //     }

  //     cardFound=true;
  //     return cardFound;
  // }

  // public List<Integer> calculateFee(Passenger passenger,Integer fee)
  // {
  //     Integer discount = 0;
  //     if(passenger.getReturnJourneyFlag()){
  //         fee=fee/2;
  //         discount += fee/2;
  //     }
  //     List<Integer> al=new ArrayList<Integer>();
  //     al.add(fee);
  //     al.add(discount);
  //     return al;
  // }

}
