package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.RaFormula;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RaFormula entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RaFormulaRepository extends JpaRepository<RaFormula, Long> {}
