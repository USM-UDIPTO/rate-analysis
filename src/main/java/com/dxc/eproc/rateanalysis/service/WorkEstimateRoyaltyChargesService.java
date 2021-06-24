package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRoyaltyChargesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyaltyCharges}.
 */
public interface WorkEstimateRoyaltyChargesService {
    /**
     * Save a workEstimateRoyaltyCharges.
     *
     * @param workEstimateRoyaltyChargesDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateRoyaltyChargesDTO save(WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO);

    /**
     * Partially updates a workEstimateRoyaltyCharges.
     *
     * @param workEstimateRoyaltyChargesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateRoyaltyChargesDTO> partialUpdate(WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO);

    /**
     * Get all the workEstimateRoyaltyCharges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateRoyaltyChargesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateRoyaltyCharges.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateRoyaltyChargesDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateRoyaltyCharges.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
