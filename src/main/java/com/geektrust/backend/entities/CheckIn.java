package com.geektrust.backend.entities;

public class CheckIn {

  private final String cardId;
  private final PassengerType passengerType;
  private final StationName fromStation;

  public CheckIn(String cardId, String passengerType, String fromStation) {
    this.cardId = cardId;
    this.passengerType = PassengerType.valueOf(passengerType);
    this.fromStation = StationName.valueOf(fromStation);
  }

  public String getCardId() {
    return this.cardId;
  }

  public PassengerType getPassengerType() {
    return this.passengerType;
  }

  public StationName getFromStation() {
    return this.fromStation;
  }
}
