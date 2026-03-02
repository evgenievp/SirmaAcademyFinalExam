package com.FinalExam.SirmaFinalExam.Dtos;

import jakarta.validation.constraints.*;
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
    @NotNull(message = "Team name is required")
    @NotBlank
    @Size(min = 1)
    private String name;
    @NotBlank(message = "Manager name is required")
    @NotNull
    @Size(min = 2)
    private String managerFullName;
    @NotNull(message = "Team Group is one char")
    @NotBlank
    private char teamGroup;

}
