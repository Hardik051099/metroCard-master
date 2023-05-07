package com.geektrust.backend.dtos;

import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;

public class TotalCollection {
    StationName stationName;
    int amountCollected;
    int discountGiven;
    PassengerType passengertype;
    int count;

    public TotalCollection(StationName stationName, int amountCollected, int discountGiven,
            PassengerType passengertype, int count) {
        this.stationName = stationName;
        this.amountCollected = amountCollected;
        this.discountGiven = discountGiven;
        this.passengertype = passengertype;
        this.count = count;
    }
    
    public StationName getStationName() {
        return stationName;
    }
    public void setStationName(StationName stationName) {
        this.stationName = stationName;
    }    
    public int getAmountCollected() {
        return amountCollected;
    }
    public void setAmountCollected(int amountCollected) {
        this.amountCollected = amountCollected;
    }
    public int getDiscountGiven() {
        return discountGiven;
    }
    public void setDiscountGiven(int discountGiven) {
        this.discountGiven = discountGiven;
    }
    public PassengerType getPassengertype() {
        return passengertype;
    }
    public void setPassengertype(PassengerType passengertype) {
        this.passengertype = passengertype;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
}