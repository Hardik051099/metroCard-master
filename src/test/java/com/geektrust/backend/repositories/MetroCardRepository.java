package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;
import com.geektrust.backend.entities.MetroCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MetroCardRepository {
    private MetroCardRepository metroCardRepository;


    @BeforeEach
    void setup(){
        final Map<String,MetroCard> cardMap = new HashMap<String,MetroCard>(){
            {
                put("MC1",new MetroCard("MC1", 100));
                put("MC2",new MetroCard("MC2", 200));
                put("MC3",new MetroCard("MC3", 150));
            }
        };
        metroCardRepository = new MetroCardRepository(cardMap);
    }

    @Test
    @DisplayName("save method should create new Card")
    public void saveCard(){
        //Arrange
        final MetroCard card4 = new MetroCard("MC4", 350);
        MetroCard expectedCard = new MetroCard("4", 350);
        //Act
        MetroCard actualUser = metroCardRepository.save(card4);
        //Assert
        Assertions.assertEquals(expectedUser,actualUser);
    }

   
}