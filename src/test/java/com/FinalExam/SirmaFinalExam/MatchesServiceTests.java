package com.FinalExam.SirmaFinalExam;

import com.FinalExam.SirmaFinalExam.Dtos.MatchDto;
import com.FinalExam.SirmaFinalExam.Models.Matches;
import com.FinalExam.SirmaFinalExam.Repos.MatchesRepo;
import com.FinalExam.SirmaFinalExam.Services.MatchesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MatchesServiceTests {

        @InjectMocks
        MatchesService MatchesService;

        @Mock
        private MatchesRepo MatchesRepo;
        private Matches testMatch;

        @BeforeEach
        void setup() {
            MockitoAnnotations.openMocks(this);
            testMatch= new Matches(

                    1,
                    2,
                    LocalDate.of(2024, 1,11),
                    3,
                    1
                    );
            testMatch.setId(1);
        }

        @Test
        void testGetMatchById() {
            when(MatchesRepo.findById(1)).thenReturn(Optional.of(testMatch));

            MatchDto dto = MatchesService.findMatchById(1);

            assertNotNull(dto);
            assertEquals(LocalDate.of(2024,1,11), dto.getDate());
        }

        @Test
        void editMatchSuccessfulEdition() {
            when(MatchesRepo.findById(1)).thenReturn(Optional.of(testMatch));
            when(MatchesRepo.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
            MatchDto matchDto = MatchesService.findMatchById(1);

            matchDto.setATeamId(1);
            matchDto.setAwayScore(1);
            matchDto.setBTeamId(2);
            matchDto.setHomeScore(3);
            matchDto.setDate(LocalDate.of(2024, 1, 13));
            MatchesService.editMatch(matchDto, 1);

            verify(MatchesRepo).save(any());

            assertEquals(LocalDate.of(2024, 1,13), matchDto.getDate());
            assertEquals(1, matchDto.getAwayScore());
            assertEquals(3, matchDto.getHomeScore());
            assertEquals(1, matchDto.getATeamId());
            assertEquals(2, matchDto.getBTeamId());
        }


}
