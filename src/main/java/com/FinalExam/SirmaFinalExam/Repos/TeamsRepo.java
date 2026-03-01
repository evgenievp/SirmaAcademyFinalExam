package com.FinalExam.SirmaFinalExam.Repos;

import com.FinalExam.SirmaFinalExam.Models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamsRepo extends JpaRepository<Teams, Integer> {

    Optional<Teams> getTeamById(int id);
}
