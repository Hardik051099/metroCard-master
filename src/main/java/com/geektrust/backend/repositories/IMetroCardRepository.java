package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.MetroCard;
import java.util.List;

public interface IMetroCardRepository {
  public List<MetroCard> findAll();

  public void save(MetroCard entity);

  // public void update(String cardNumber,Integer remainingBalance,String Id);
  MetroCard getCard(String cardNumber);
}
