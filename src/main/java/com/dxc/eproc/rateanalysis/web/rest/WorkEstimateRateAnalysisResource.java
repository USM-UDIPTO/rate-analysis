package com.dxc.eproc.rateanalysis.web.rest;

import com.dxc.eproc.rateanalysis.repository.WorkEstimateRateAnalysisRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateRateAnalysisService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRateAnalysisDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateRateAnalysis}.
 */
@RestController
@RequestMapping("/api")
public class WorkEstimateRateAnalysisResource {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateRateAnalysisResource.class);

    private static final String ENTITY_NAME = "rateAnalysisWorkEstimateRateAnalysis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkEstimateRateAnalysisService workEstimateRateAnalysisService;

    private final WorkEstimateRateAnalysisRepository workEstimateRateAnalysisRepository;

    public WorkEstimateRateAnalysisResource(
        WorkEstimateRateAnalysisService workEstimateRateAnalysisService,
        WorkEstimateRateAnalysisRepository workEstimateRateAnalysisRepository
    ) {
        this.workEstimateRateAnalysisService = workEstimateRateAnalysisService;
        this.workEstimateRateAnalysisRepository = workEstimateRateAnalysisRepository;
    }

    /**
     * {@code POST  /work-estimate-rate-analyses} : Create a new workEstimateRateAnalysis.
     *
     * @param workEstimateRateAnalysisDTO the workEstimateRateAnalysisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workEstimateRateAnalysisDTO, or with status {@code 400 (Bad Request)} if the workEstimateRateAnalysis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-estimate-rate-analyses")
    public ResponseEntity<WorkEstimateRateAnalysisDTO> createWorkEstimateRateAnalysis(
        @Valid @RequestBody WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkEstimateRateAnalysis : {}", workEstimateRateAnalysisDTO);
        if (workEstimateRateAnalysisDTO.getId() != null) {
            throw new BadRequestAlertException("A new workEstimateRateAnalysis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkEstimateRateAnalysisDTO result = workEstimateRateAnalysisService.save(workEstimateRateAnalysisDTO);
        return ResponseEntity
            .created(new URI("/api/work-estimate-rate-analyses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-estimate-rate-analyses/:id} : Updates an existing workEstimateRateAnalysis.
     *
     * @param id the id of the workEstimateRateAnalysisDTO to save.
     * @param workEstimateRateAnalysisDTO the workEstimateRateAnalysisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateRateAnalysisDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateRateAnalysisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateRateAnalysisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-estimate-rate-analyses/{id}")
    public ResponseEntity<WorkEstimateRateAnalysisDTO> updateWorkEstimateRateAnalysis(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkEstimateRateAnalysis : {}, {}", id, workEstimateRateAnalysisDTO);
        if (workEstimateRateAnalysisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateRateAnalysisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateRateAnalysisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkEstimateRateAnalysisDTO result = workEstimateRateAnalysisService.save(workEstimateRateAnalysisDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateRateAnalysisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /work-estimate-rate-analyses/:id} : Partial updates given fields of an existing workEstimateRateAnalysis, field will ignore if it is null
     *
     * @param id the id of the workEstimateRateAnalysisDTO to save.
     * @param workEstimateRateAnalysisDTO the workEstimateRateAnalysisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateRateAnalysisDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateRateAnalysisDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workEstimateRateAnalysisDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateRateAnalysisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/work-estimate-rate-analyses/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<WorkEstimateRateAnalysisDTO> partialUpdateWorkEstimateRateAnalysis(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkEstimateRateAnalysis partially : {}, {}", id, workEstimateRateAnalysisDTO);
        if (workEstimateRateAnalysisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateRateAnalysisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateRateAnalysisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkEstimateRateAnalysisDTO> result = workEstimateRateAnalysisService.partialUpdate(workEstimateRateAnalysisDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateRateAnalysisDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /work-estimate-rate-analyses} : get all the workEstimateRateAnalyses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workEstimateRateAnalyses in body.
     */
    @GetMapping("/work-estimate-rate-analyses")
    public ResponseEntity<List<WorkEstimateRateAnalysisDTO>> getAllWorkEstimateRateAnalyses(Pageable pageable) {
        log.debug("REST request to get a page of WorkEstimateRateAnalyses");
        Page<WorkEstimateRateAnalysisDTO> page = workEstimateRateAnalysisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-estimate-rate-analyses/:id} : get the "id" workEstimateRateAnalysis.
     *
     * @param id the id of the workEstimateRateAnalysisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workEstimateRateAnalysisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-estimate-rate-analyses/{id}")
    public ResponseEntity<WorkEstimateRateAnalysisDTO> getWorkEstimateRateAnalysis(@PathVariable Long id) {
        log.debug("REST request to get WorkEstimateRateAnalysis : {}", id);
        Optional<WorkEstimateRateAnalysisDTO> workEstimateRateAnalysisDTO = workEstimateRateAnalysisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workEstimateRateAnalysisDTO);
    }

    /**
     * {@code DELETE  /work-estimate-rate-analyses/:id} : delete the "id" workEstimateRateAnalysis.
     *
     * @param id the id of the workEstimateRateAnalysisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-estimate-rate-analyses/{id}")
    public ResponseEntity<Void> deleteWorkEstimateRateAnalysis(@PathVariable Long id) {
        log.debug("REST request to delete WorkEstimateRateAnalysis : {}", id);
        workEstimateRateAnalysisService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
