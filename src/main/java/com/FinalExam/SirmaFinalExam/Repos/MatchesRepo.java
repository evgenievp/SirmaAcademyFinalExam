package com.FinalExam.SirmaFinalExam.Repos;

import com.FinalExam.SirmaFinalExam.Models.Matches;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MatchesRepo extends JpaRepository<Matches, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            drop table matches;
            """)
    void dropTable();
}
