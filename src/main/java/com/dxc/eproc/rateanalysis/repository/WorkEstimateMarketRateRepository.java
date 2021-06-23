package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateMarketRate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateMarketRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateMarketRateRepository extends JpaRepository<WorkEstimateMarketRate, Long> {}
