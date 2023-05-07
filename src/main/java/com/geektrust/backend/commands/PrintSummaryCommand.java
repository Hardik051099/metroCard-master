package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.IMetroCardService;
import com.geektrust.backend.services.IRevenueService;

public class PrintSummaryCommand implements ICommand{
    private final IMetroCardService metroCardService;
    private final IRevenueService revenueService;

    public PrintSummaryCommand(IMetroCardService metroCardService,IRevenueService revenueService) {
        this.metroCardService = metroCardService;
        this.revenueService=revenueService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        revenueService.printSummary();    
    }
}
