package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateLeadCharges;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateLeadCharges entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateLeadChargesRepository extends JpaRepository<WorkEstimateLeadCharges, Long> {}
