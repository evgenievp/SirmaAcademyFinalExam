package com.FinalExam.SirmaFinalExam.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchDto {
    //ATeamId, BTeamId, Date, Score
    @Min(value = 1)
    @NotNull(message = "Team A Id must be valid")
    private int ATeamId;
    @Min(value = 1)
    @NotNull(message = "Team A Id must be valid")
    private int BTeamId;
    @NotNull
    @NotBlank
    private LocalDate date;
    @Min(value = 0)
    private int homeScore;
    @Min(value = 0)
    private int awayScore;

}
