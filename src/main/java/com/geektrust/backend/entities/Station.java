package com.geektrust.backend.entities;

public class Station {

  private final String stopName;

  public Station(String stopName) {
    this.stopName = stopName;
  }

  public String getStopName() {
    return stopName;
  }

  public void recharge(MetroCard card) {
    //calculateRecharge();

  }
}
