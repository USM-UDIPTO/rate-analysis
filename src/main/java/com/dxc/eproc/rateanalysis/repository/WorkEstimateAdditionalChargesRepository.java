package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateAdditionalCharges;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateAdditionalCharges entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateAdditionalChargesRepository extends JpaRepository<WorkEstimateAdditionalCharges, Long> {}
