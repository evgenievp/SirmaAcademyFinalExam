package com.FinalExam.SirmaFinalExam.Dtos;

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
    private int ATeamId;
    private int BTeamId;
    private LocalDate date;
    private int homeScore;
    private int awayScore;

}
