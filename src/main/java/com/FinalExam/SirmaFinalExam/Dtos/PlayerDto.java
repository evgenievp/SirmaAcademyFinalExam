package com.FinalExam.SirmaFinalExam.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PlayerDto {
    //teamNumber, position, fullname,teamid
    private int teamNumber;
    private String position;
    private String fullname;
    private int teamId;

}
