package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.RaParametersDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.RaParameters}.
 */
public interface RaParametersService {
    /**
     * Save a raParameters.
     *
     * @param raParametersDTO the entity to save.
     * @return the persisted entity.
     */
    RaParametersDTO save(RaParametersDTO raParametersDTO);

    /**
     * Partially updates a raParameters.
     *
     * @param raParametersDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RaParametersDTO> partialUpdate(RaParametersDTO raParametersDTO);

    /**
     * Get all the raParameters.
     *
     * @return the list of entities.
     */
    List<RaParametersDTO> findAll();

    /**
     * Get the "id" raParameters.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RaParametersDTO> findOne(Long id);

    /**
     * Delete the "id" raParameters.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
