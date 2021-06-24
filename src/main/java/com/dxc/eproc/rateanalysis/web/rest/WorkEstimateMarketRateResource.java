package com.dxc.eproc.rateanalysis.web.rest;

import com.dxc.eproc.rateanalysis.repository.WorkEstimateMarketRateRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateMarketRateService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateMarketRateDTO;
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
 * REST controller for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateMarketRate}.
 */
@RestController
@RequestMapping("/api")
public class WorkEstimateMarketRateResource {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateMarketRateResource.class);

    private static final String ENTITY_NAME = "rateAnalysisWorkEstimateMarketRate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkEstimateMarketRateService workEstimateMarketRateService;

    private final WorkEstimateMarketRateRepository workEstimateMarketRateRepository;

    public WorkEstimateMarketRateResource(
        WorkEstimateMarketRateService workEstimateMarketRateService,
        WorkEstimateMarketRateRepository workEstimateMarketRateRepository
    ) {
        this.workEstimateMarketRateService = workEstimateMarketRateService;
        this.workEstimateMarketRateRepository = workEstimateMarketRateRepository;
    }

    /**
     * {@code POST  /work-estimate-market-rates} : Create a new workEstimateMarketRate.
     *
     * @param workEstimateMarketRateDTO the workEstimateMarketRateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workEstimateMarketRateDTO, or with status {@code 400 (Bad Request)} if the workEstimateMarketRate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-estimate-market-rates")
    public ResponseEntity<WorkEstimateMarketRateDTO> createWorkEstimateMarketRate(
        @Valid @RequestBody WorkEstimateMarketRateDTO workEstimateMarketRateDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkEstimateMarketRate : {}", workEstimateMarketRateDTO);
        if (workEstimateMarketRateDTO.getId() != null) {
            throw new BadRequestAlertException("A new workEstimateMarketRate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkEstimateMarketRateDTO result = workEstimateMarketRateService.save(workEstimateMarketRateDTO);
        return ResponseEntity
            .created(new URI("/api/work-estimate-market-rates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-estimate-market-rates/:id} : Updates an existing workEstimateMarketRate.
     *
     * @param id the id of the workEstimateMarketRateDTO to save.
     * @param workEstimateMarketRateDTO the workEstimateMarketRateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateMarketRateDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateMarketRateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateMarketRateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-estimate-market-rates/{id}")
    public ResponseEntity<WorkEstimateMarketRateDTO> updateWorkEstimateMarketRate(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkEstimateMarketRateDTO workEstimateMarketRateDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkEstimateMarketRate : {}, {}", id, workEstimateMarketRateDTO);
        if (workEstimateMarketRateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateMarketRateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateMarketRateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkEstimateMarketRateDTO result = workEstimateMarketRateService.save(workEstimateMarketRateDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateMarketRateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /work-estimate-market-rates/:id} : Partial updates given fields of an existing workEstimateMarketRate, field will ignore if it is null
     *
     * @param id the id of the workEstimateMarketRateDTO to save.
     * @param workEstimateMarketRateDTO the workEstimateMarketRateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateMarketRateDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateMarketRateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workEstimateMarketRateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateMarketRateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/work-estimate-market-rates/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<WorkEstimateMarketRateDTO> partialUpdateWorkEstimateMarketRate(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkEstimateMarketRateDTO workEstimateMarketRateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkEstimateMarketRate partially : {}, {}", id, workEstimateMarketRateDTO);
        if (workEstimateMarketRateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateMarketRateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateMarketRateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkEstimateMarketRateDTO> result = workEstimateMarketRateService.partialUpdate(workEstimateMarketRateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateMarketRateDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /work-estimate-market-rates} : get all the workEstimateMarketRates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workEstimateMarketRates in body.
     */
    @GetMapping("/work-estimate-market-rates")
    public ResponseEntity<List<WorkEstimateMarketRateDTO>> getAllWorkEstimateMarketRates(Pageable pageable) {
        log.debug("REST request to get a page of WorkEstimateMarketRates");
        Page<WorkEstimateMarketRateDTO> page = workEstimateMarketRateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-estimate-market-rates/:id} : get the "id" workEstimateMarketRate.
     *
     * @param id the id of the workEstimateMarketRateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workEstimateMarketRateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-estimate-market-rates/{id}")
    public ResponseEntity<WorkEstimateMarketRateDTO> getWorkEstimateMarketRate(@PathVariable Long id) {
        log.debug("REST request to get WorkEstimateMarketRate : {}", id);
        Optional<WorkEstimateMarketRateDTO> workEstimateMarketRateDTO = workEstimateMarketRateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workEstimateMarketRateDTO);
    }

    /**
     * {@code DELETE  /work-estimate-market-rates/:id} : delete the "id" workEstimateMarketRate.
     *
     * @param id the id of the workEstimateMarketRateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-estimate-market-rates/{id}")
    public ResponseEntity<Void> deleteWorkEstimateMarketRate(@PathVariable Long id) {
        log.debug("REST request to delete WorkEstimateMarketRate : {}", id);
        workEstimateMarketRateService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
