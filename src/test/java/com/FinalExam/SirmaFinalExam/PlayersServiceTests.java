package com.FinalExam.SirmaFinalExam;



import com.FinalExam.SirmaFinalExam.Dtos.PlayerDto;
import com.FinalExam.SirmaFinalExam.Models.Players;
import com.FinalExam.SirmaFinalExam.Repos.PlayersRepo;
import com.FinalExam.SirmaFinalExam.Services.PlayersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayersServiceTests {

    @InjectMocks
    PlayersService playersService;

    @Mock
    private PlayersRepo playersRepo;
    private Players testPlayer;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        testPlayer = new Players(
                10,
                "MF",
                "John Doe",
                1
        );
        testPlayer.setId(1);
    }

    @Test
    void testGetPlayerByIdFound() {
        when(playersRepo.findById(1)).thenReturn(Optional.of(testPlayer));

        PlayerDto opt = playersService.findPlayerById(1);

        assertNotNull(opt);
        assertEquals("John Doe", opt.getFullName());
    }

    @Test
    void editPlayerSuccessfulEdition() {
        when(playersRepo.findById(1)).thenReturn(Optional.of(testPlayer));
        when(playersRepo.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        PlayerDto pl = playersService.findPlayerById(1);

        pl.setFullName("John Anyone");
        pl.setPosition("DF");
        pl.setTeamId(2);
        pl.setTeamNumber(97);

        playersService.editPlayer(pl, 1);

        verify(playersRepo).save(any());

        assertEquals("DF", testPlayer.getPosition());
        assertEquals("John Anyone", testPlayer.getFullName());
        assertEquals(2, testPlayer.getTeamId());
        assertEquals(97, testPlayer.getTeamNumber());
    }

}


