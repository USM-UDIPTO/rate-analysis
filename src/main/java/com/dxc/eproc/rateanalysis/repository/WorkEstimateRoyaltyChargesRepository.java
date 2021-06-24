package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyaltyCharges;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateRoyaltyCharges entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateRoyaltyChargesRepository extends JpaRepository<WorkEstimateRoyaltyCharges, Long> {}
