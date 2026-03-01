package com.FinalExam.SirmaFinalExam.Repos;

import com.FinalExam.SirmaFinalExam.Dtos.MatchDto;
import com.FinalExam.SirmaFinalExam.Models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MatchesRepo extends JpaRepository<Matches, Integer> {
    Optional<Matches> getMatchById(int id);
}
