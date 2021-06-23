package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRateAnalysisDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateRateAnalysis}.
 */
public interface WorkEstimateRateAnalysisService {
    /**
     * Save a workEstimateRateAnalysis.
     *
     * @param workEstimateRateAnalysisDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateRateAnalysisDTO save(WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO);

    /**
     * Partially updates a workEstimateRateAnalysis.
     *
     * @param workEstimateRateAnalysisDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateRateAnalysisDTO> partialUpdate(WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO);

    /**
     * Get all the workEstimateRateAnalyses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateRateAnalysisDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateRateAnalysis.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateRateAnalysisDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateRateAnalysis.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
