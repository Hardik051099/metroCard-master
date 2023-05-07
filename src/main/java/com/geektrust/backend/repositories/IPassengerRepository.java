package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;
import java.util.Map;
import java.util.Optional;

public interface IPassengerRepository {
  // public Passenger save(Passenger entity);
  // public Optional<Passenger> findById(String integer);
  // public void update(String id, MetroCard card, String passengerType,Boolean returnJourneyFlag);
  void checkInPassenger(
    String cardId,
    StationName fromStation,
    PassengerType passengerType,
    int amount
  );
  Map<String, Integer> getStationAmountMap();
  Map<String, Integer> getStationDiscountMap();
  Map<StationName, Map<PassengerType, Integer>> getStationTypeCountMap();
}
