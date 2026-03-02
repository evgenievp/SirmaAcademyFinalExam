package com.FinalExam.SirmaFinalExam.Dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotBlank
    @Min(value = 1)
    private int playerId;
    @NotNull
    @NotBlank
    @Min(value = 1)
    private int matchId;
    @NotNull
    @NotBlank
    @Min(value = 1)
    private int fromMinutes;
    @NotNull
    @NotBlank
    @Min(value = 0)
    @Max(value = 90) // Since we are working with regular time and without additional minutes - 90 is max minutes of playtime
    private int toMinutes;
}
