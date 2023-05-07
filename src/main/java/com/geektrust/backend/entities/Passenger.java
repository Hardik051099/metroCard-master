package com.geektrust.backend.entities;

public class Passenger {
    private final String id;
    private final MetroCard card;
    private String passengerType;
    private Boolean returnJourneyFlag;

    
    public Passenger(String id, MetroCard card, String passengerType,
            Boolean returnJourneyFlag) {
        this.id = id;
        this.card = card;
        this.passengerType = passengerType;
        this.returnJourneyFlag = returnJourneyFlag;
    }
    public String getId() {
        return id;
    }
    public MetroCard getCard() {
        return card;
    }
    public String getPassengerType() {
        return passengerType;
    }
    public Boolean getReturnJourneyFlag() {
        return returnJourneyFlag;
    }
}
