package com.geektrust.backend.services;

import com.geektrust.backend.entities.CheckIn;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;
import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import java.util.*;

public interface IPassengerService {
  // public void checkIn(String cardNum,String passengerType,String fromStation) throws CardNumberNotFoundException;
  /*
        Checks-in a passenger
        @CheckIn object
    */
  void checkInPassenger(
    String cardId,
    String passengerType,
    String fromStation
  );

  /*
        return station type along with count
        @return map - amount left to pay
    */
  Map<StationName, Map<PassengerType, Integer>> getStationTypeCountMap();

  /*
        returns station vs amount map
        @return map - amount paid mapped to station
    */
  Map<String, Integer> getStationAmountMap();

  /*
        returns station vs amount map
        @return map - amount paid mapped to station
    */
  Map<String, Integer> getStationDiscountMap();
}
