package com.FinalExam.SirmaFinalExam.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordsDto {

//playerId, matchId, fromMinutes, toMinutes

    private int playerId;
    private int matchId;
    private int fromMinutes;
    private int toMinutes;
}
