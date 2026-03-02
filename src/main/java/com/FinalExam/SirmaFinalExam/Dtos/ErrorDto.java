package com.FinalExam.SirmaFinalExam.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDto {
    private int status;
    private String message;
    private LocalDateTime time;

}
