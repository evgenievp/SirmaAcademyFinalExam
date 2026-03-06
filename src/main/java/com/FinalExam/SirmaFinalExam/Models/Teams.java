package com.FinalExam.SirmaFinalExam.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// id, name, managerFullName, teamGroup
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min=2)
    @NotBlank
    @NotNull
    private String name;
    @Size(min=2, max = 100, message = "full name must be between 2 and 100 characters long.")
    @NotBlank
    @NotNull
    @Column(name = "manager_full_name")
    private String managerFullName;
    @NotNull
    @Column(name = "team_group")
    private char teamGroup;

    public Teams(String name, String managerFullName, char teamGroup) {
        this.name = name;
        this.managerFullName = managerFullName;
        this.teamGroup = teamGroup;
    }


}
