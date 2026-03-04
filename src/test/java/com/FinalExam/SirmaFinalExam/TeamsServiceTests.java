package com.FinalExam.SirmaFinalExam;

import com.FinalExam.SirmaFinalExam.Dtos.TeamDto;
import com.FinalExam.SirmaFinalExam.Models.Teams;
import com.FinalExam.SirmaFinalExam.Repos.TeamsRepo;
import com.FinalExam.SirmaFinalExam.Services.TeamsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeamsServiceTests {

        @InjectMocks
        TeamsService teamsService;

        @Mock
        private TeamsRepo TeamsRepo;
        private Teams testTeam;

        @BeforeEach
        void setup() {
            MockitoAnnotations.openMocks(this);
            testTeam = new Teams(
                    1,
            "Barcelona",
            "Joseph Guardiola",
            'A');
            testTeam.setId(1);
        }

        @Test
        void testGetTeamById() {
            when(TeamsRepo.findById(1)).thenReturn(Optional.of(testTeam));

            TeamDto teamDTo = teamsService.findTeamById(1);

            assertNotNull(teamDTo);
            assertEquals("Barcelona", teamDTo.getName());
        }

        @Test
        void editTeamSuccessfulEdition() {
            when(TeamsRepo.findById(1)).thenReturn(Optional.of(testTeam));
            when(TeamsRepo.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
            TeamDto teamDto = teamsService.findTeamById(1);

            teamDto.setManagerFullName("Alex Ferguson");
            teamDto.setName("Manchester United");
            teamDto.setTeamGroup('C');

            teamsService.editTeam(teamDto, 1);

            verify(TeamsRepo).save(any());

            assertEquals("Manchester United", teamDto.getName());
            assertEquals("Alex Ferguson", teamDto.getManagerFullName());
            assertEquals('C', teamDto.getTeamGroup());

        }

    }

