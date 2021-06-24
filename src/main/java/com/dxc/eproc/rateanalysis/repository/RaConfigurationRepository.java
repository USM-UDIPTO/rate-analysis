package com.dxc.eproc.rateanalysis.repository;

import com.dxc.eproc.rateanalysis.domain.RaConfiguration;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RaConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RaConfigurationRepository extends JpaRepository<RaConfiguration, Long> {}
