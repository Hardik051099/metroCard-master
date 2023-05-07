package com.geektrust.backend.commands;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import com.geektrust.backend.services.MetroCardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@DisplayName("CheckInCommandTest")
@ExtendWith(MockitoExtension.class)

public class CheckInCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    MetroCardService metroCardServiceMock;

    @InjectMocks
    CheckInCommand checkInCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of CheckInCommand Should Print Error Message To Console If Card Not Found")
    public void execute_ShouldPrintErrorMessage_GivenCardNotFound() {
        //Arrange
        String cardNum = "1";
        String expectedOutput = "Cannot check in. card num:"+ cardNum +" not found!";
        doThrow(new CardNumberNotFoundException(expectedOutput)).when(metroCardServiceMock).attendContest(contestId,"Joey");

        //Act
        checkInCommand.execute(List.of("ATTEND-CONTEST",cardNum,"Joey"));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(userServiceMock,times(1)).attendContest(contestId,"Joey");
    }

}
