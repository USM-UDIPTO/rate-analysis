package com.dxc.eproc.rateanalysis.web.rest;

import com.dxc.eproc.rateanalysis.repository.WorkEstimateAdditionalChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateAdditionalChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateAdditionalChargesDTO;
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
 * REST controller for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateAdditionalCharges}.
 */
@RestController
@RequestMapping("/api")
public class WorkEstimateAdditionalChargesResource {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateAdditionalChargesResource.class);

    private static final String ENTITY_NAME = "rateAnalysisWorkEstimateAdditionalCharges";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkEstimateAdditionalChargesService workEstimateAdditionalChargesService;

    private final WorkEstimateAdditionalChargesRepository workEstimateAdditionalChargesRepository;

    public WorkEstimateAdditionalChargesResource(
        WorkEstimateAdditionalChargesService workEstimateAdditionalChargesService,
        WorkEstimateAdditionalChargesRepository workEstimateAdditionalChargesRepository
    ) {
        this.workEstimateAdditionalChargesService = workEstimateAdditionalChargesService;
        this.workEstimateAdditionalChargesRepository = workEstimateAdditionalChargesRepository;
    }

    /**
     * {@code POST  /work-estimate-additional-charges} : Create a new workEstimateAdditionalCharges.
     *
     * @param workEstimateAdditionalChargesDTO the workEstimateAdditionalChargesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workEstimateAdditionalChargesDTO, or with status {@code 400 (Bad Request)} if the workEstimateAdditionalCharges has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-estimate-additional-charges")
    public ResponseEntity<WorkEstimateAdditionalChargesDTO> createWorkEstimateAdditionalCharges(
        @Valid @RequestBody WorkEstimateAdditionalChargesDTO workEstimateAdditionalChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkEstimateAdditionalCharges : {}", workEstimateAdditionalChargesDTO);
        if (workEstimateAdditionalChargesDTO.getId() != null) {
            throw new BadRequestAlertException("A new workEstimateAdditionalCharges cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkEstimateAdditionalChargesDTO result = workEstimateAdditionalChargesService.save(workEstimateAdditionalChargesDTO);
        return ResponseEntity
            .created(new URI("/api/work-estimate-additional-charges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-estimate-additional-charges/:id} : Updates an existing workEstimateAdditionalCharges.
     *
     * @param id the id of the workEstimateAdditionalChargesDTO to save.
     * @param workEstimateAdditionalChargesDTO the workEstimateAdditionalChargesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateAdditionalChargesDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateAdditionalChargesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateAdditionalChargesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-estimate-additional-charges/{id}")
    public ResponseEntity<WorkEstimateAdditionalChargesDTO> updateWorkEstimateAdditionalCharges(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkEstimateAdditionalChargesDTO workEstimateAdditionalChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkEstimateAdditionalCharges : {}, {}", id, workEstimateAdditionalChargesDTO);
        if (workEstimateAdditionalChargesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateAdditionalChargesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateAdditionalChargesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkEstimateAdditionalChargesDTO result = workEstimateAdditionalChargesService.save(workEstimateAdditionalChargesDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateAdditionalChargesDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /work-estimate-additional-charges/:id} : Partial updates given fields of an existing workEstimateAdditionalCharges, field will ignore if it is null
     *
     * @param id the id of the workEstimateAdditionalChargesDTO to save.
     * @param workEstimateAdditionalChargesDTO the workEstimateAdditionalChargesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateAdditionalChargesDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateAdditionalChargesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workEstimateAdditionalChargesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateAdditionalChargesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/work-estimate-additional-charges/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<WorkEstimateAdditionalChargesDTO> partialUpdateWorkEstimateAdditionalCharges(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkEstimateAdditionalChargesDTO workEstimateAdditionalChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkEstimateAdditionalCharges partially : {}, {}", id, workEstimateAdditionalChargesDTO);
        if (workEstimateAdditionalChargesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateAdditionalChargesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateAdditionalChargesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkEstimateAdditionalChargesDTO> result = workEstimateAdditionalChargesService.partialUpdate(
            workEstimateAdditionalChargesDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateAdditionalChargesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /work-estimate-additional-charges} : get all the workEstimateAdditionalCharges.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workEstimateAdditionalCharges in body.
     */
    @GetMapping("/work-estimate-additional-charges")
    public ResponseEntity<List<WorkEstimateAdditionalChargesDTO>> getAllWorkEstimateAdditionalCharges(Pageable pageable) {
        log.debug("REST request to get a page of WorkEstimateAdditionalCharges");
        Page<WorkEstimateAdditionalChargesDTO> page = workEstimateAdditionalChargesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-estimate-additional-charges/:id} : get the "id" workEstimateAdditionalCharges.
     *
     * @param id the id of the workEstimateAdditionalChargesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workEstimateAdditionalChargesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-estimate-additional-charges/{id}")
    public ResponseEntity<WorkEstimateAdditionalChargesDTO> getWorkEstimateAdditionalCharges(@PathVariable Long id) {
        log.debug("REST request to get WorkEstimateAdditionalCharges : {}", id);
        Optional<WorkEstimateAdditionalChargesDTO> workEstimateAdditionalChargesDTO = workEstimateAdditionalChargesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workEstimateAdditionalChargesDTO);
    }

    /**
     * {@code DELETE  /work-estimate-additional-charges/:id} : delete the "id" workEstimateAdditionalCharges.
     *
     * @param id the id of the workEstimateAdditionalChargesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-estimate-additional-charges/{id}")
    public ResponseEntity<Void> deleteWorkEstimateAdditionalCharges(@PathVariable Long id) {
        log.debug("REST request to delete WorkEstimateAdditionalCharges : {}", id);
        workEstimateAdditionalChargesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
