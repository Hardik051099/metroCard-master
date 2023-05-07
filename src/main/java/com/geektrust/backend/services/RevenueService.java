package com.geektrust.backend.services;

import com.geektrust.backend.dtos.TotalCollection;
import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;
import com.geektrust.backend.repositories.IRevenueRepository;
import java.util.*;

public class RevenueService implements IRevenueService {

  private final IPassengerService passengerService;

  public RevenueService(IPassengerService passengerService) {
    this.passengerService = passengerService;
  }

  @Override
  public void printSummary() {
    // List<TotalCollection> allCollection = revenueRepository.findAll().orElse(new ArrayList<>());

    //     // KID 2
    //     int amountCollectedAtCentral = 0;
    //     int discountGivenAtCentral = 0;
    //     int seniorCentralCount = 0;
    //     int adultCentralCount = 0;
    //     int kidCentralCount = 0;

    //     int amountCollectedAtAirport = 0;
    //     int discountGivenAtAirport = 0;
    //     int adultAirportCount = 0;
    //     int seniorAirportCount = 0;
    //     int kidAirportCount = 0;

    // for(TotalCollection t: allCollection){
    //     if(t.getStationName() == StationName.AIRPORT){
    //             amountCollectedAtAirport+=t.getAmountCollected();
    //             discountGivenAtAirport+=t.getDiscountGiven();
    //             switch(t.getPassengertype()){
    //                 case ADULT:
    //                     adultAirportCount+=t.getCount();
    //                     break;
    //                 case KID:
    //                     kidAirportCount+=t.getCount();
    //                     break;
    //                 case SENIOR_CITIZEN:
    //                     seniorAirportCount+=t.getCount();
    //                     break;
    //                 default:
    //             }
    //     }else {
    //         amountCollectedAtCentral+=t.getAmountCollected();
    //         discountGivenAtCentral+=t.getDiscountGiven();
    //         switch(t.getPassengertype()){
    //             case ADULT:
    //                 adultCentralCount+=t.getCount();
    //                 break;
    //             case KID:
    //                 kidCentralCount+=t.getCount();
    //                 break;
    //             case SENIOR_CITIZEN:
    //                 seniorCentralCount+=t.getCount();
    //                 break;
    //             default:
    //         }
    //     }
    // }

    //        System.out.println("TOTAL_COLLECTION CENTRAL " + amountCollectedAtCentral + " " + discountGivenAtCentral);
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

    Map<String, Integer> stationAmountMap = passengerService.getStationAmountMap();
    Map<String, Integer> stationDiscountMap = passengerService.getStationDiscountMap();
    Map<StationName, Map<PassengerType, Integer>> stationTypeCountMap = passengerService.getStationTypeCountMap();

    for (StationName StationName : StationName.values()) {
      System.out.println(
        "TOTAL_COLLECTION " +
        StationName.name() +
        " " +
        stationAmountMap.get(StationName.name()) +
        " " +
        stationDiscountMap.get(StationName.name())
      );
      System.out.println("PASSENGER_TYPE_SUMMARY");
      PriorityQueue<PassengerCount> sortedCount = convertToQueue(
        stationTypeCountMap.get(StationName)
      );
      while (!sortedCount.isEmpty()) {
        PassengerCount passengerCount = sortedCount.poll();
        System.out.println(
          passengerCount.getPassengerType().name() +
          " " +
          passengerCount.getCount()
        );
      }
    }
  }

  private PriorityQueue<PassengerCount> convertToQueue(
    Map<PassengerType, Integer> map
  ) {
    PriorityQueue<PassengerCount> priorityQueue = new PriorityQueue<>();
    for (Map.Entry<PassengerType, Integer> entry : map.entrySet()) {
      priorityQueue.add(new PassengerCount(entry.getKey(), entry.getValue()));
    }
    return priorityQueue;
  }
}
