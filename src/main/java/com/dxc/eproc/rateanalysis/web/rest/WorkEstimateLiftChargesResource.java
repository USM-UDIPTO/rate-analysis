package com.dxc.eproc.rateanalysis.web.rest;

import com.dxc.eproc.rateanalysis.repository.WorkEstimateLiftChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateLiftChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLiftChargesDTO;
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
 * REST controller for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateLiftCharges}.
 */
@RestController
@RequestMapping("/api")
public class WorkEstimateLiftChargesResource {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateLiftChargesResource.class);

    private static final String ENTITY_NAME = "rateAnalysisWorkEstimateLiftCharges";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkEstimateLiftChargesService workEstimateLiftChargesService;

    private final WorkEstimateLiftChargesRepository workEstimateLiftChargesRepository;

    public WorkEstimateLiftChargesResource(
        WorkEstimateLiftChargesService workEstimateLiftChargesService,
        WorkEstimateLiftChargesRepository workEstimateLiftChargesRepository
    ) {
        this.workEstimateLiftChargesService = workEstimateLiftChargesService;
        this.workEstimateLiftChargesRepository = workEstimateLiftChargesRepository;
    }

    /**
     * {@code POST  /work-estimate-lift-charges} : Create a new workEstimateLiftCharges.
     *
     * @param workEstimateLiftChargesDTO the workEstimateLiftChargesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workEstimateLiftChargesDTO, or with status {@code 400 (Bad Request)} if the workEstimateLiftCharges has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-estimate-lift-charges")
    public ResponseEntity<WorkEstimateLiftChargesDTO> createWorkEstimateLiftCharges(
        @Valid @RequestBody WorkEstimateLiftChargesDTO workEstimateLiftChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkEstimateLiftCharges : {}", workEstimateLiftChargesDTO);
        if (workEstimateLiftChargesDTO.getId() != null) {
            throw new BadRequestAlertException("A new workEstimateLiftCharges cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkEstimateLiftChargesDTO result = workEstimateLiftChargesService.save(workEstimateLiftChargesDTO);
        return ResponseEntity
            .created(new URI("/api/work-estimate-lift-charges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-estimate-lift-charges/:id} : Updates an existing workEstimateLiftCharges.
     *
     * @param id the id of the workEstimateLiftChargesDTO to save.
     * @param workEstimateLiftChargesDTO the workEstimateLiftChargesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateLiftChargesDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateLiftChargesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateLiftChargesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-estimate-lift-charges/{id}")
    public ResponseEntity<WorkEstimateLiftChargesDTO> updateWorkEstimateLiftCharges(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkEstimateLiftChargesDTO workEstimateLiftChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkEstimateLiftCharges : {}, {}", id, workEstimateLiftChargesDTO);
        if (workEstimateLiftChargesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateLiftChargesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateLiftChargesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkEstimateLiftChargesDTO result = workEstimateLiftChargesService.save(workEstimateLiftChargesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateLiftChargesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /work-estimate-lift-charges/:id} : Partial updates given fields of an existing workEstimateLiftCharges, field will ignore if it is null
     *
     * @param id the id of the workEstimateLiftChargesDTO to save.
     * @param workEstimateLiftChargesDTO the workEstimateLiftChargesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateLiftChargesDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateLiftChargesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workEstimateLiftChargesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateLiftChargesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/work-estimate-lift-charges/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<WorkEstimateLiftChargesDTO> partialUpdateWorkEstimateLiftCharges(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkEstimateLiftChargesDTO workEstimateLiftChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkEstimateLiftCharges partially : {}, {}", id, workEstimateLiftChargesDTO);
        if (workEstimateLiftChargesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateLiftChargesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateLiftChargesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkEstimateLiftChargesDTO> result = workEstimateLiftChargesService.partialUpdate(workEstimateLiftChargesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateLiftChargesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /work-estimate-lift-charges} : get all the workEstimateLiftCharges.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workEstimateLiftCharges in body.
     */
    @GetMapping("/work-estimate-lift-charges")
    public ResponseEntity<List<WorkEstimateLiftChargesDTO>> getAllWorkEstimateLiftCharges(Pageable pageable) {
        log.debug("REST request to get a page of WorkEstimateLiftCharges");
        Page<WorkEstimateLiftChargesDTO> page = workEstimateLiftChargesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-estimate-lift-charges/:id} : get the "id" workEstimateLiftCharges.
     *
     * @param id the id of the workEstimateLiftChargesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workEstimateLiftChargesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-estimate-lift-charges/{id}")
    public ResponseEntity<WorkEstimateLiftChargesDTO> getWorkEstimateLiftCharges(@PathVariable Long id) {
        log.debug("REST request to get WorkEstimateLiftCharges : {}", id);
        Optional<WorkEstimateLiftChargesDTO> workEstimateLiftChargesDTO = workEstimateLiftChargesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workEstimateLiftChargesDTO);
    }

    /**
     * {@code DELETE  /work-estimate-lift-charges/:id} : delete the "id" workEstimateLiftCharges.
     *
     * @param id the id of the workEstimateLiftChargesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-estimate-lift-charges/{id}")
    public ResponseEntity<Void> deleteWorkEstimateLiftCharges(@PathVariable Long id) {
        log.debug("REST request to delete WorkEstimateLiftCharges : {}", id);
        workEstimateLiftChargesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
