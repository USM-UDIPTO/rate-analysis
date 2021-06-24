package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLeadChargesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateLeadCharges}.
 */
public interface WorkEstimateLeadChargesService {
    /**
     * Save a workEstimateLeadCharges.
     *
     * @param workEstimateLeadChargesDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateLeadChargesDTO save(WorkEstimateLeadChargesDTO workEstimateLeadChargesDTO);

    /**
     * Partially updates a workEstimateLeadCharges.
     *
     * @param workEstimateLeadChargesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateLeadChargesDTO> partialUpdate(WorkEstimateLeadChargesDTO workEstimateLeadChargesDTO);

    /**
     * Get all the workEstimateLeadCharges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateLeadChargesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateLeadCharges.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateLeadChargesDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateLeadCharges.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
