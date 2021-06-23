package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLoadUnloadChargesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateLoadUnloadCharges}.
 */
public interface WorkEstimateLoadUnloadChargesService {
    /**
     * Save a workEstimateLoadUnloadCharges.
     *
     * @param workEstimateLoadUnloadChargesDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateLoadUnloadChargesDTO save(WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO);

    /**
     * Partially updates a workEstimateLoadUnloadCharges.
     *
     * @param workEstimateLoadUnloadChargesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateLoadUnloadChargesDTO> partialUpdate(WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO);

    /**
     * Get all the workEstimateLoadUnloadCharges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateLoadUnloadChargesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateLoadUnloadCharges.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateLoadUnloadChargesDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateLoadUnloadCharges.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
