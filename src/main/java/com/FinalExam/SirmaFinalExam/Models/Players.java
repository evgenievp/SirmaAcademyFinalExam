package com.FinalExam.SirmaFinalExam.Models;
//id, teamNumber, position, fullname,teamid

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "players")
@AllArgsConstructor
@NoArgsConstructor
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(value = 1)
    @Max(value = 99)
    private int teamNumber;
    @NotBlank
    @NotNull
    private String position;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100, message = "full name must be between 2 and 100 characters long.")
    private String fullName; // I won't make this unique. As far as I know there are two players with same names.
    @Min(value = 1)
    @Column(name = "team_id")
    private int teamId;


    public Players(int teamNumber, String position, String fullName, int teamId) {
        this.teamNumber = teamNumber;
        this.position = position;
        this.fullName = fullName;
        this.teamId = teamId;
    }

    public int getId() {
        return this.id;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

}
