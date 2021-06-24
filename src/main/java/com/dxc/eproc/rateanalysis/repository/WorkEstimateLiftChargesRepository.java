package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateLiftCharges;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateLiftCharges entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateLiftChargesRepository extends JpaRepository<WorkEstimateLiftCharges, Long> {}
