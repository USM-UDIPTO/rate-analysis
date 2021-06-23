package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLeadDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateLead}.
 */
public interface WorkEstimateLeadService {
    /**
     * Save a workEstimateLead.
     *
     * @param workEstimateLeadDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateLeadDTO save(WorkEstimateLeadDTO workEstimateLeadDTO);

    /**
     * Partially updates a workEstimateLead.
     *
     * @param workEstimateLeadDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateLeadDTO> partialUpdate(WorkEstimateLeadDTO workEstimateLeadDTO);

    /**
     * Get all the workEstimateLeads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateLeadDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateLead.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateLeadDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateLead.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
