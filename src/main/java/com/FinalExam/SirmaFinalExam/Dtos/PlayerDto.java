package com.FinalExam.SirmaFinalExam.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Min(value = 1)
    private int teamNumber;
    @Size(min = 2)
    @NotNull
    @NotBlank(message = "player position is required")
    private String position;
    @NotBlank(message = "player name should be added")
    @NotNull
    @Size(min = 2)
    private String fullName;
    @Min(value = 1)
    @NotNull
    private int teamId;

}
