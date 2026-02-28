package com.FinalExam.SirmaFinalExam.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamDto {

    //name, managerFullName, teamGroup
    private String name;
    private String managerFullName;
    private char teamGroup;

}
