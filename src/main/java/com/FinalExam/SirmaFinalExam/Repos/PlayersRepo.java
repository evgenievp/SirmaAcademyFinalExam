package com.FinalExam.SirmaFinalExam.Repos;

import com.FinalExam.SirmaFinalExam.Models.Players;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayersRepo extends JpaRepository<Players, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            drop table players;
            """)
    void dropTable();
}
