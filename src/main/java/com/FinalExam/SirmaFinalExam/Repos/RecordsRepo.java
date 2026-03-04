package com.FinalExam.SirmaFinalExam.Repos;

import com.FinalExam.SirmaFinalExam.Models.Records;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecordsRepo extends JpaRepository<Records, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            drop table records;
            """)
    void dropTable();
}
