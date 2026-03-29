package com.founderlink.startupservice.repository;

import com.founderlink.startupservice.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StartupRepository extends JpaRepository<Startup, Long> {

    List<Startup> findByIndustry(String industry);

    List<Startup> findByStage(StartupStage stage);

    @Query("SELECT s FROM Startup s WHERE " +
            "(:industry IS NULL OR s.industry = :industry) AND " +
            "(:stage IS NULL OR s.stage = :stage) AND " +
            "s.approvalStatus = 'APPROVED'")
    List<Startup> search(@Param("industry") String industry,
                         @Param("stage") String stage);
}