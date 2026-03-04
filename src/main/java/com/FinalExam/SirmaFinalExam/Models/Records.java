package com.FinalExam.SirmaFinalExam.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//id, playerId, matchId, fromMinutes, toMinutes
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    public int getId() {
        return this.id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getFromMinutes() {
        return fromMinutes;
    }

    public void setFromMinutes(int fromMinutes) {
        this.fromMinutes = fromMinutes;
    }

    public int getToMinutes() {
        return toMinutes;
    }

    public void setToMinutes(int toMinutes) {
        this.toMinutes = toMinutes;
    }
}
