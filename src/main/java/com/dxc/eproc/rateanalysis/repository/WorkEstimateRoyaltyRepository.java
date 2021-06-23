package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyalty;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateRoyalty entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateRoyaltyRepository extends JpaRepository<WorkEstimateRoyalty, Long> {}
