package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.RaConfigurationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.RaConfiguration}.
 */
public interface RaConfigurationService {
    /**
     * Save a raConfiguration.
     *
     * @param raConfigurationDTO the entity to save.
     * @return the persisted entity.
     */
    RaConfigurationDTO save(RaConfigurationDTO raConfigurationDTO);

    /**
     * Partially updates a raConfiguration.
     *
     * @param raConfigurationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RaConfigurationDTO> partialUpdate(RaConfigurationDTO raConfigurationDTO);

    /**
     * Get all the raConfigurations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RaConfigurationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" raConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RaConfigurationDTO> findOne(Long id);

    /**
     * Delete the "id" raConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
