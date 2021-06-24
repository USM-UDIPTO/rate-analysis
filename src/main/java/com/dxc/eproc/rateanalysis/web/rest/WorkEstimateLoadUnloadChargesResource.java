package com.dxc.eproc.rateanalysis.web.rest;

import com.dxc.eproc.rateanalysis.repository.WorkEstimateLoadUnloadChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateLoadUnloadChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLoadUnloadChargesDTO;
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
 * REST controller for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateLoadUnloadCharges}.
 */
@RestController
@RequestMapping("/api")
public class WorkEstimateLoadUnloadChargesResource {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateLoadUnloadChargesResource.class);

    private static final String ENTITY_NAME = "rateAnalysisWorkEstimateLoadUnloadCharges";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkEstimateLoadUnloadChargesService workEstimateLoadUnloadChargesService;

    private final WorkEstimateLoadUnloadChargesRepository workEstimateLoadUnloadChargesRepository;

    public WorkEstimateLoadUnloadChargesResource(
        WorkEstimateLoadUnloadChargesService workEstimateLoadUnloadChargesService,
        WorkEstimateLoadUnloadChargesRepository workEstimateLoadUnloadChargesRepository
    ) {
        this.workEstimateLoadUnloadChargesService = workEstimateLoadUnloadChargesService;
        this.workEstimateLoadUnloadChargesRepository = workEstimateLoadUnloadChargesRepository;
    }

    /**
     * {@code POST  /work-estimate-load-unload-charges} : Create a new workEstimateLoadUnloadCharges.
     *
     * @param workEstimateLoadUnloadChargesDTO the workEstimateLoadUnloadChargesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workEstimateLoadUnloadChargesDTO, or with status {@code 400 (Bad Request)} if the workEstimateLoadUnloadCharges has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-estimate-load-unload-charges")
    public ResponseEntity<WorkEstimateLoadUnloadChargesDTO> createWorkEstimateLoadUnloadCharges(
        @Valid @RequestBody WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkEstimateLoadUnloadCharges : {}", workEstimateLoadUnloadChargesDTO);
        if (workEstimateLoadUnloadChargesDTO.getId() != null) {
            throw new BadRequestAlertException("A new workEstimateLoadUnloadCharges cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkEstimateLoadUnloadChargesDTO result = workEstimateLoadUnloadChargesService.save(workEstimateLoadUnloadChargesDTO);
        return ResponseEntity
            .created(new URI("/api/work-estimate-load-unload-charges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-estimate-load-unload-charges/:id} : Updates an existing workEstimateLoadUnloadCharges.
     *
     * @param id the id of the workEstimateLoadUnloadChargesDTO to save.
     * @param workEstimateLoadUnloadChargesDTO the workEstimateLoadUnloadChargesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateLoadUnloadChargesDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateLoadUnloadChargesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateLoadUnloadChargesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-estimate-load-unload-charges/{id}")
    public ResponseEntity<WorkEstimateLoadUnloadChargesDTO> updateWorkEstimateLoadUnloadCharges(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkEstimateLoadUnloadCharges : {}, {}", id, workEstimateLoadUnloadChargesDTO);
        if (workEstimateLoadUnloadChargesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateLoadUnloadChargesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateLoadUnloadChargesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkEstimateLoadUnloadChargesDTO result = workEstimateLoadUnloadChargesService.save(workEstimateLoadUnloadChargesDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateLoadUnloadChargesDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /work-estimate-load-unload-charges/:id} : Partial updates given fields of an existing workEstimateLoadUnloadCharges, field will ignore if it is null
     *
     * @param id the id of the workEstimateLoadUnloadChargesDTO to save.
     * @param workEstimateLoadUnloadChargesDTO the workEstimateLoadUnloadChargesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateLoadUnloadChargesDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateLoadUnloadChargesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workEstimateLoadUnloadChargesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateLoadUnloadChargesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/work-estimate-load-unload-charges/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<WorkEstimateLoadUnloadChargesDTO> partialUpdateWorkEstimateLoadUnloadCharges(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkEstimateLoadUnloadCharges partially : {}, {}", id, workEstimateLoadUnloadChargesDTO);
        if (workEstimateLoadUnloadChargesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateLoadUnloadChargesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateLoadUnloadChargesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkEstimateLoadUnloadChargesDTO> result = workEstimateLoadUnloadChargesService.partialUpdate(
            workEstimateLoadUnloadChargesDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateLoadUnloadChargesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /work-estimate-load-unload-charges} : get all the workEstimateLoadUnloadCharges.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workEstimateLoadUnloadCharges in body.
     */
    @GetMapping("/work-estimate-load-unload-charges")
    public ResponseEntity<List<WorkEstimateLoadUnloadChargesDTO>> getAllWorkEstimateLoadUnloadCharges(Pageable pageable) {
        log.debug("REST request to get a page of WorkEstimateLoadUnloadCharges");
        Page<WorkEstimateLoadUnloadChargesDTO> page = workEstimateLoadUnloadChargesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-estimate-load-unload-charges/:id} : get the "id" workEstimateLoadUnloadCharges.
     *
     * @param id the id of the workEstimateLoadUnloadChargesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workEstimateLoadUnloadChargesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-estimate-load-unload-charges/{id}")
    public ResponseEntity<WorkEstimateLoadUnloadChargesDTO> getWorkEstimateLoadUnloadCharges(@PathVariable Long id) {
        log.debug("REST request to get WorkEstimateLoadUnloadCharges : {}", id);
        Optional<WorkEstimateLoadUnloadChargesDTO> workEstimateLoadUnloadChargesDTO = workEstimateLoadUnloadChargesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workEstimateLoadUnloadChargesDTO);
    }

    /**
     * {@code DELETE  /work-estimate-load-unload-charges/:id} : delete the "id" workEstimateLoadUnloadCharges.
     *
     * @param id the id of the workEstimateLoadUnloadChargesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-estimate-load-unload-charges/{id}")
    public ResponseEntity<Void> deleteWorkEstimateLoadUnloadCharges(@PathVariable Long id) {
        log.debug("REST request to delete WorkEstimateLoadUnloadCharges : {}", id);
        workEstimateLoadUnloadChargesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
