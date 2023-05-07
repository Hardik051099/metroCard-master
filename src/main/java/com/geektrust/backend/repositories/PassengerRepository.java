package com.geektrust.backend.repositories;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PassengerRepository implements IPassengerRepository {

  private Map<String, Integer> stationAmountMap;
  private Map<String, Integer> stationDiscountMap;
  private Map<StationName, Map<PassengerType, Integer>> stationTypeCountMap;
  private Map<String, StationName> passengerMap;

  public PassengerRepository() {
    stationAmountMap = new HashMap<>();
    stationDiscountMap = new HashMap<>();
    stationTypeCountMap = new HashMap<>();
    for (StationName StationName : StationName.values()) {
      stationAmountMap.put(StationName.name(), Common.ZERO);
      stationDiscountMap.put(StationName.name(), Common.ZERO);
      stationTypeCountMap.put(StationName, new HashMap<>());
    }
    passengerMap = new HashMap<>();
  }

  @Override
  public void checkInPassenger(
    String cardId,
    StationName fromStation,
    PassengerType passengerType,
    int amount
  ) {
    if (passengerMap.containsKey(cardId)) {
      int collection = stationAmountMap.get(fromStation.name()) + amount;
      int discount = stationDiscountMap.get(fromStation.name()) + amount;
      stationDiscountMap.put(fromStation.name(), discount);

      if (amount != Common.ZERO) {
        collection += amount * Common.PERCENTAGE;
      }
      stationAmountMap.put(fromStation.name(), collection);
      passengerMap.remove(cardId);
    } else {
      int collection = stationAmountMap.get(fromStation.name()) + amount;

      if (amount != Common.ZERO) {
        collection += amount * Common.PERCENTAGE;
      }
      stationAmountMap.put(fromStation.name(), collection);
      passengerMap.put(cardId, fromStation);
    }

    updatePassengerCount(
      stationTypeCountMap.get(fromStation),
      passengerType,
      fromStation
    );
  }

  private void updatePassengerCount(
    Map<PassengerType, Integer> tempMap,
    PassengerType passengerType,
    StationName StationName
  ) {
    if (tempMap.containsKey(passengerType)) {
      int val = tempMap.get(passengerType) + 1;
      tempMap.put(passengerType, val);
    } else {
      tempMap.put(passengerType, 1);
    }
    stationTypeCountMap.put(StationName, tempMap);
  }

  @Override
  public Map<String, Integer> getStationAmountMap() {
    return this.stationAmountMap;
  }

  @Override
  public Map<String, Integer> getStationDiscountMap() {
    return this.stationDiscountMap;
  }

  @Override
  public Map<StationName, Map<PassengerType, Integer>> getStationTypeCountMap() {
    return this.stationTypeCountMap;
  }
  // @Override
  // public Optional<Passenger> findById(String id) {
  //     return Optional.ofNullable(passengerMap.get(id));
  // }

  // @Override
  // public Passenger save(Passenger entity) {
  //     // TODO Auto-generated method stub
  //     return null;
  // }

  // @Override
  // public void update(String id, MetroCard card, String passengerType,
  // Boolean returnJourneyFlag) {
  //     // TODO Auto-generated method stub
  //     if(returnJourneyFlag){
  //         Passenger passenger=new Passenger(id, card, passengerType ,!returnJourneyFlag);
  //         passengerMap.put(id, passenger);
  //     }
  // }

}
