package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateOtherAddnLiftCharges;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkEstimateOtherAddnLiftCharges entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkEstimateOtherAddnLiftChargesRepository extends JpaRepository<WorkEstimateOtherAddnLiftCharges, Long> {}
