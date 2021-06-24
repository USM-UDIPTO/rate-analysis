package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateMarketRateDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateMarketRate}.
 */
public interface WorkEstimateMarketRateService {
    /**
     * Save a workEstimateMarketRate.
     *
     * @param workEstimateMarketRateDTO the entity to save.
     * @return the persisted entity.
     */
    WorkEstimateMarketRateDTO save(WorkEstimateMarketRateDTO workEstimateMarketRateDTO);

    /**
     * Partially updates a workEstimateMarketRate.
     *
     * @param workEstimateMarketRateDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WorkEstimateMarketRateDTO> partialUpdate(WorkEstimateMarketRateDTO workEstimateMarketRateDTO);

    /**
     * Get all the workEstimateMarketRates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkEstimateMarketRateDTO> findAll(Pageable pageable);

    /**
     * Get the "id" workEstimateMarketRate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkEstimateMarketRateDTO> findOne(Long id);

    /**
     * Delete the "id" workEstimateMarketRate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
