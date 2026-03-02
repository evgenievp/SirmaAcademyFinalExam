package com.FinalExam.SirmaFinalExam.services;

import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Repos.PlayersRepo;
import com.FinalExam.SirmaFinalExam.Services.PlayersService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public class PlayersServiceTests {
    @Mock
    private PlayersRepo playersRepo;

    @InjectMocks
    PlayersService playersService;

    @Test
    public void testGetPlayerExpectValidResult() {

        Players mockPlayer = new Players(
                7,
                "forward",
                "Nani",
                2
        );

        when(playersRepo.save(mockPlayer)).thenReturn(null);




    }
}
