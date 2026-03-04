package com.FinalExam.SirmaFinalExam.Repos;

import com.FinalExam.SirmaFinalExam.Models.Teams;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeamsRepo extends JpaRepository<Teams, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            drop table teams;
            """)
    public void dropTable();
}
