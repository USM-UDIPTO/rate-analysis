package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateLoadUnloadCharges;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateLoadUnloadCharges entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateLoadUnloadChargesRepository extends JpaRepository<WorkEstimateLoadUnloadCharges, Long> {}
