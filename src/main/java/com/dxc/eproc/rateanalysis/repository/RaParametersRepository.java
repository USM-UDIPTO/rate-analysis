package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.RaParameters;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RaParameters entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RaParametersRepository extends JpaRepository<RaParameters, Long> {}
