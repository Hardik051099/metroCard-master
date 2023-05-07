package com.geektrust.backend.commands;

import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import com.geektrust.backend.services.IMetroCardService;
import java.util.List;

public class BalanceCommand implements ICommand {

  private final IMetroCardService metroCardService;

  public BalanceCommand(IMetroCardService metroCardService) {
    this.metroCardService = metroCardService;
  }

  @Override
  public void execute(List<String> tokens) {
    // TODO Auto-generated method stub
    try {
      metroCardService.addCard(tokens.get(1), Integer.parseInt(tokens.get(2)));
    } catch (CardNumberNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }
}
