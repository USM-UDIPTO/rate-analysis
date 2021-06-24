package com.dxc.eproc.rateanalysis.service;

import com.dxc.eproc.rateanalysis.service.dto.RaFormulaDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dxc.eproc.rateanalysis.domain.RaFormula}.
 */
public interface RaFormulaService {
    /**
     * Save a raFormula.
     *
     * @param raFormulaDTO the entity to save.
     * @return the persisted entity.
     */
    RaFormulaDTO save(RaFormulaDTO raFormulaDTO);

    /**
     * Partially updates a raFormula.
     *
     * @param raFormulaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RaFormulaDTO> partialUpdate(RaFormulaDTO raFormulaDTO);

    /**
     * Get all the raFormulas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RaFormulaDTO> findAll(Pageable pageable);

    /**
     * Get the "id" raFormula.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RaFormulaDTO> findOne(Long id);

    /**
     * Delete the "id" raFormula.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
