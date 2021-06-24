package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateRateAnalysis;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateRateAnalysis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateRateAnalysisRepository extends JpaRepository<WorkEstimateRateAnalysis, Long> {}
