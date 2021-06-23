package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateLead;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateLead entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateLeadRepository extends JpaRepository<WorkEstimateLead, Long> {}
