package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.BalanceCommand;
import com.geektrust.backend.commands.CheckInCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.PrintSummaryCommand;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IPassengerRepository;
import com.geektrust.backend.repositories.IRevenueRepository;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.PassengerRepository;
import com.geektrust.backend.repositories.RevenueRepository;
import com.geektrust.backend.services.IMetroCardService;
import com.geektrust.backend.services.IPassengerService;
import com.geektrust.backend.services.IRevenueService;
import com.geektrust.backend.services.MetroCardService;
import com.geektrust.backend.services.PassengerService;
import com.geektrust.backend.services.RevenueService;

public class ApplicationConfig {

  private final IMetroCardRepository metroCardRepository = new MetroCardRepository();
  private final IRevenueRepository revenueRepository = new RevenueRepository();

  private final IMetroCardService metroCardService = new MetroCardService(
    metroCardRepository
  );
  private final IPassengerRepository passengerRepository = new PassengerRepository(
    metroCardService
  );

  private final IPassengerService passengerService = new PassengerService(
    metroCardRepository,
    passengerRepository,
    revenueRepository,
    metroCardService
  );
  private final IRevenueService revenueService = new RevenueService(
    passengerService
  );

  private final BalanceCommand balanceCommand = new BalanceCommand(
    metroCardService
  );
  private final CheckInCommand checkInCommand = new CheckInCommand(
    metroCardService,
    passengerService
  );
  private final PrintSummaryCommand printSummaryCommand = new PrintSummaryCommand(
    metroCardService,
    revenueService
  );

  private final CommandInvoker commandInvoker = new CommandInvoker();

  public CommandInvoker getCommandInvoker() {
    commandInvoker.register("BALANCE", balanceCommand);
    commandInvoker.register("CHECK_IN", checkInCommand);
    commandInvoker.register("PRINT_SUMMARY", printSummaryCommand);

    return commandInvoker;
  }
}
