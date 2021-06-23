package com.dxc.eproc.rateanalysis.web.rest;

import com.dxc.eproc.rateanalysis.repository.RaFormulaRepository;
import com.dxc.eproc.rateanalysis.service.RaFormulaService;
import com.dxc.eproc.rateanalysis.service.dto.RaFormulaDTO;
import com.dxc.eproc.rateanalysis.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dxc.eproc.rateanalysis.domain.RaFormula}.
 */
@RestController
@RequestMapping("/api")
public class RaFormulaResource {

    private final Logger log = LoggerFactory.getLogger(RaFormulaResource.class);

    private static final String ENTITY_NAME = "rateAnalysisRaFormula";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RaFormulaService raFormulaService;

    private final RaFormulaRepository raFormulaRepository;

    public RaFormulaResource(RaFormulaService raFormulaService, RaFormulaRepository raFormulaRepository) {
        this.raFormulaService = raFormulaService;
        this.raFormulaRepository = raFormulaRepository;
    }

    /**
     * {@code POST  /ra-formulas} : Create a new raFormula.
     *
     * @param raFormulaDTO the raFormulaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new raFormulaDTO, or with status {@code 400 (Bad Request)} if the raFormula has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ra-formulas")
    public ResponseEntity<RaFormulaDTO> createRaFormula(@Valid @RequestBody RaFormulaDTO raFormulaDTO) throws URISyntaxException {
        log.debug("REST request to save RaFormula : {}", raFormulaDTO);
        if (raFormulaDTO.getId() != null) {
            throw new BadRequestAlertException("A new raFormula cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RaFormulaDTO result = raFormulaService.save(raFormulaDTO);
        return ResponseEntity
            .created(new URI("/api/ra-formulas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ra-formulas/:id} : Updates an existing raFormula.
     *
     * @param id the id of the raFormulaDTO to save.
     * @param raFormulaDTO the raFormulaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated raFormulaDTO,
     * or with status {@code 400 (Bad Request)} if the raFormulaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the raFormulaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ra-formulas/{id}")
    public ResponseEntity<RaFormulaDTO> updateRaFormula(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RaFormulaDTO raFormulaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RaFormula : {}, {}", id, raFormulaDTO);
        if (raFormulaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, raFormulaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!raFormulaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RaFormulaDTO result = raFormulaService.save(raFormulaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, raFormulaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ra-formulas/:id} : Partial updates given fields of an existing raFormula, field will ignore if it is null
     *
     * @param id the id of the raFormulaDTO to save.
     * @param raFormulaDTO the raFormulaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated raFormulaDTO,
     * or with status {@code 400 (Bad Request)} if the raFormulaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the raFormulaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the raFormulaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ra-formulas/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RaFormulaDTO> partialUpdateRaFormula(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RaFormulaDTO raFormulaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RaFormula partially : {}, {}", id, raFormulaDTO);
        if (raFormulaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, raFormulaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!raFormulaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RaFormulaDTO> result = raFormulaService.partialUpdate(raFormulaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, raFormulaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ra-formulas} : get all the raFormulas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of raFormulas in body.
     */
    @GetMapping("/ra-formulas")
    public List<RaFormulaDTO> getAllRaFormulas() {
        log.debug("REST request to get all RaFormulas");
        return raFormulaService.findAll();
    }

    /**
     * {@code GET  /ra-formulas/:id} : get the "id" raFormula.
     *
     * @param id the id of the raFormulaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the raFormulaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ra-formulas/{id}")
    public ResponseEntity<RaFormulaDTO> getRaFormula(@PathVariable Long id) {
        log.debug("REST request to get RaFormula : {}", id);
        Optional<RaFormulaDTO> raFormulaDTO = raFormulaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(raFormulaDTO);
    }

    /**
     * {@code DELETE  /ra-formulas/:id} : delete the "id" raFormula.
     *
     * @param id the id of the raFormulaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ra-formulas/{id}")
    public ResponseEntity<Void> deleteRaFormula(@PathVariable Long id) {
        log.debug("REST request to delete RaFormula : {}", id);
        raFormulaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
