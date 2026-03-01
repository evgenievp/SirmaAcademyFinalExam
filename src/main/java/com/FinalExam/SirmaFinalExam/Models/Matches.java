package com.FinalExam.SirmaFinalExam.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
//id, ATeamId, BTeamId, Date, Score

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints= {
    @UniqueConstraint(columnNames = {"a_team_id", "b_team_id"})
})
public class Matches {
    @Id
    private int id;
    @Min(value = 1)
    private int ATeamId;
    @Min(value = 1)
    private int BTeamId;
    private LocalDate date;
    private int HomeTeamGoals;
    private int AwayTeamGoals;

    public Matches(int ATeamId,
                   int BTeamId,
                   LocalDate date,
                   int homeTeamGoals,
                   int awayTeamGoals) {
        this.ATeamId = ATeamId;
        this.BTeamId = BTeamId;
        this.date = date;
        this.HomeTeamGoals = homeTeamGoals;
        this.AwayTeamGoals = awayTeamGoals;
    }

    public int getId() {
        return this.id;
    }

    public int getATeamId() {
        return ATeamId;
    }

    public void setATeamId(int ATeamId) {
        this.ATeamId = ATeamId;
    }

    public int getBTeamId() {
        return BTeamId;
    }

    public void setBTeamId(int BTeamId) {
        this.BTeamId = BTeamId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHomeTeamGoals() {
        return HomeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        HomeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return AwayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        AwayTeamGoals = awayTeamGoals;
    }

    @PrePersist
    @PreUpdate
    private void validateTeams(){
        if (getATeamId() == getBTeamId()) {
            throw new IllegalArgumentException("Teams must be different.");
        }
    }

}
