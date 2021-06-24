package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateAdditionalChargesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateAdditionalCharges}.
 */
public interface WorkEstimateAdditionalChargesService {
    /**
     * Save a workEstimateAdditionalCharges.
     *
     * @param workEstimateAdditionalChargesDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateAdditionalChargesDTO save(WorkEstimateAdditionalChargesDTO workEstimateAdditionalChargesDTO);

    /**
     * Partially updates a workEstimateAdditionalCharges.
     *
     * @param workEstimateAdditionalChargesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateAdditionalChargesDTO> partialUpdate(WorkEstimateAdditionalChargesDTO workEstimateAdditionalChargesDTO);

    /**
     * Get all the workEstimateAdditionalCharges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateAdditionalChargesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateAdditionalCharges.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateAdditionalChargesDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateAdditionalCharges.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
