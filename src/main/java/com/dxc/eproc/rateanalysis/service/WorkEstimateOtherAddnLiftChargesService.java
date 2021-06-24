package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateOtherAddnLiftChargesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateOtherAddnLiftCharges}.
 */
public interface WorkEstimateOtherAddnLiftChargesService {
    /**
     * Save a workEstimateOtherAddnLiftCharges.
     *
     * @param workEstimateOtherAddnLiftChargesDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateOtherAddnLiftChargesDTO save(WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO);

    /**
     * Partially updates a workEstimateOtherAddnLiftCharges.
     *
     * @param workEstimateOtherAddnLiftChargesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateOtherAddnLiftChargesDTO> partialUpdate(WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO);

    /**
     * Get all the workEstimateOtherAddnLiftCharges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateOtherAddnLiftChargesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateOtherAddnLiftCharges.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateOtherAddnLiftChargesDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateOtherAddnLiftCharges.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
