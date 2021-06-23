package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRoyaltyDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyalty}.
 */
public interface WorkEstimateRoyaltyService {
    /**
     * Save a workEstimateRoyalty.
     *
     * @param workEstimateRoyaltyDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateRoyaltyDTO save(WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO);

    /**
     * Partially updates a workEstimateRoyalty.
     *
     * @param workEstimateRoyaltyDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateRoyaltyDTO> partialUpdate(WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO);

    /**
     * Get all the workEstimateRoyalties.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateRoyaltyDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateRoyalty.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateRoyaltyDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateRoyalty.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
