package com.FinalExam.SirmaFinalExam.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//id, playerId, matchId, fromMinutes, toMinutes
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(value = 1)
    @Column(name = "player_id")
    private int playerId;
    @Min(value = 1)
    private int matchId;
    @Min(value = 0)
    private int fromMinutes;
    private int toMinutes; // this can be null, but I will always read it as 90th minute


    public Records(int playerId, int matchId, int fromMinutes, int toMinutes) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.fromMinutes = fromMinutes;
        this.toMinutes = toMinutes;
    }

}
