package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.geektrust.backend.dtos.TotalCollection;

public class RevenueRepository implements IRevenueRepository{

    private final Map<String,TotalCollection> revenueMap;
    private Integer autoIncrement = 0;
    
    public RevenueRepository() {
        revenueMap = new HashMap<String,TotalCollection>();
    }

    public RevenueRepository(Map<String, TotalCollection> revenueMap) {
        this.revenueMap = revenueMap;
        this.autoIncrement = revenueMap.size();
    }

    @Override
    public TotalCollection save(TotalCollection entity) {
        // TODO Auto-generated method stub
        String key = entity.getStationName()+"_"+entity.getPassengertype();
        TotalCollection totalCollection = new TotalCollection(entity.getStationName(),entity.getAmountCollected(),
        entity.getDiscountGiven(),entity.getPassengertype(),entity.getCount());
        if(revenueMap.containsKey(key)){
            TotalCollection currentRecord = revenueMap.get(key);
            currentRecord.setCount(currentRecord.getCount()+entity.getCount());
            currentRecord.setAmountCollected(currentRecord.getAmountCollected()+entity.getAmountCollected());
            currentRecord.setDiscountGiven(currentRecord.getDiscountGiven()+entity.getDiscountGiven());
            revenueMap.put(key,currentRecord);
        }
        else{
            revenueMap.put(key,totalCollection);
        }
        return totalCollection;

    }
    

    @Override
    public Optional<TotalCollection> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(revenueMap.get(id));
    }
    @Override
    public Optional<List<TotalCollection>> findAll() {
        // TODO Auto-generated method stub
        return Optional.ofNullable(revenueMap.values().stream().collect(Collectors.toList()));
    }
    
}
