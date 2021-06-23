package com.dxc.eproc.rateanalysis.web.rest;

import com.dxc.eproc.rateanalysis.repository.WorkEstimateOtherAddnLiftChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateOtherAddnLiftChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateOtherAddnLiftChargesDTO;
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
 * REST controller for managing {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateOtherAddnLiftCharges}.
 */
@RestController
@RequestMapping("/api")
public class WorkEstimateOtherAddnLiftChargesResource {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateOtherAddnLiftChargesResource.class);

    private static final String ENTITY_NAME = "rateAnalysisWorkEstimateOtherAddnLiftCharges";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkEstimateOtherAddnLiftChargesService workEstimateOtherAddnLiftChargesService;

    private final WorkEstimateOtherAddnLiftChargesRepository workEstimateOtherAddnLiftChargesRepository;

    public WorkEstimateOtherAddnLiftChargesResource(
        WorkEstimateOtherAddnLiftChargesService workEstimateOtherAddnLiftChargesService,
        WorkEstimateOtherAddnLiftChargesRepository workEstimateOtherAddnLiftChargesRepository
    ) {
        this.workEstimateOtherAddnLiftChargesService = workEstimateOtherAddnLiftChargesService;
        this.workEstimateOtherAddnLiftChargesRepository = workEstimateOtherAddnLiftChargesRepository;
    }

    /**
     * {@code POST  /work-estimate-other-addn-lift-charges} : Create a new workEstimateOtherAddnLiftCharges.
     *
     * @param workEstimateOtherAddnLiftChargesDTO the workEstimateOtherAddnLiftChargesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workEstimateOtherAddnLiftChargesDTO, or with status {@code 400 (Bad Request)} if the workEstimateOtherAddnLiftCharges has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-estimate-other-addn-lift-charges")
    public ResponseEntity<WorkEstimateOtherAddnLiftChargesDTO> createWorkEstimateOtherAddnLiftCharges(
        @Valid @RequestBody WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkEstimateOtherAddnLiftCharges : {}", workEstimateOtherAddnLiftChargesDTO);
        if (workEstimateOtherAddnLiftChargesDTO.getId() != null) {
            throw new BadRequestAlertException("A new workEstimateOtherAddnLiftCharges cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkEstimateOtherAddnLiftChargesDTO result = workEstimateOtherAddnLiftChargesService.save(workEstimateOtherAddnLiftChargesDTO);
        return ResponseEntity
            .created(new URI("/api/work-estimate-other-addn-lift-charges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-estimate-other-addn-lift-charges/:id} : Updates an existing workEstimateOtherAddnLiftCharges.
     *
     * @param id the id of the workEstimateOtherAddnLiftChargesDTO to save.
     * @param workEstimateOtherAddnLiftChargesDTO the workEstimateOtherAddnLiftChargesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateOtherAddnLiftChargesDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateOtherAddnLiftChargesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateOtherAddnLiftChargesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-estimate-other-addn-lift-charges/{id}")
    public ResponseEntity<WorkEstimateOtherAddnLiftChargesDTO> updateWorkEstimateOtherAddnLiftCharges(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkEstimateOtherAddnLiftCharges : {}, {}", id, workEstimateOtherAddnLiftChargesDTO);
        if (workEstimateOtherAddnLiftChargesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateOtherAddnLiftChargesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateOtherAddnLiftChargesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkEstimateOtherAddnLiftChargesDTO result = workEstimateOtherAddnLiftChargesService.save(workEstimateOtherAddnLiftChargesDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    workEstimateOtherAddnLiftChargesDTO.getId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /work-estimate-other-addn-lift-charges/:id} : Partial updates given fields of an existing workEstimateOtherAddnLiftCharges, field will ignore if it is null
     *
     * @param id the id of the workEstimateOtherAddnLiftChargesDTO to save.
     * @param workEstimateOtherAddnLiftChargesDTO the workEstimateOtherAddnLiftChargesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workEstimateOtherAddnLiftChargesDTO,
     * or with status {@code 400 (Bad Request)} if the workEstimateOtherAddnLiftChargesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workEstimateOtherAddnLiftChargesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workEstimateOtherAddnLiftChargesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/work-estimate-other-addn-lift-charges/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<WorkEstimateOtherAddnLiftChargesDTO> partialUpdateWorkEstimateOtherAddnLiftCharges(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update WorkEstimateOtherAddnLiftCharges partially : {}, {}",
            id,
            workEstimateOtherAddnLiftChargesDTO
        );
        if (workEstimateOtherAddnLiftChargesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workEstimateOtherAddnLiftChargesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workEstimateOtherAddnLiftChargesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkEstimateOtherAddnLiftChargesDTO> result = workEstimateOtherAddnLiftChargesService.partialUpdate(
            workEstimateOtherAddnLiftChargesDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workEstimateOtherAddnLiftChargesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /work-estimate-other-addn-lift-charges} : get all the workEstimateOtherAddnLiftCharges.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workEstimateOtherAddnLiftCharges in body.
     */
    @GetMapping("/work-estimate-other-addn-lift-charges")
    public ResponseEntity<List<WorkEstimateOtherAddnLiftChargesDTO>> getAllWorkEstimateOtherAddnLiftCharges(Pageable pageable) {
        log.debug("REST request to get a page of WorkEstimateOtherAddnLiftCharges");
        Page<WorkEstimateOtherAddnLiftChargesDTO> page = workEstimateOtherAddnLiftChargesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-estimate-other-addn-lift-charges/:id} : get the "id" workEstimateOtherAddnLiftCharges.
     *
     * @param id the id of the workEstimateOtherAddnLiftChargesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workEstimateOtherAddnLiftChargesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-estimate-other-addn-lift-charges/{id}")
    public ResponseEntity<WorkEstimateOtherAddnLiftChargesDTO> getWorkEstimateOtherAddnLiftCharges(@PathVariable Long id) {
        log.debug("REST request to get WorkEstimateOtherAddnLiftCharges : {}", id);
        Optional<WorkEstimateOtherAddnLiftChargesDTO> workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(workEstimateOtherAddnLiftChargesDTO);
    }

    /**
     * {@code DELETE  /work-estimate-other-addn-lift-charges/:id} : delete the "id" workEstimateOtherAddnLiftCharges.
     *
     * @param id the id of the workEstimateOtherAddnLiftChargesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-estimate-other-addn-lift-charges/{id}")
    public ResponseEntity<Void> deleteWorkEstimateOtherAddnLiftCharges(@PathVariable Long id) {
        log.debug("REST request to delete WorkEstimateOtherAddnLiftCharges : {}", id);
        workEstimateOtherAddnLiftChargesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
