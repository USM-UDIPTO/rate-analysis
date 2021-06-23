package com.dxc.eproc.rateanalysis.web.rest;

import com.dxc.eproc.rateanalysis.repository.RaConfigurationRepository;
import com.dxc.eproc.rateanalysis.service.RaConfigurationService;
import com.dxc.eproc.rateanalysis.service.dto.RaConfigurationDTO;
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
 * REST controller for managing {@link com.dxc.eproc.rateanalysis.domain.RaConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class RaConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(RaConfigurationResource.class);

    private static final String ENTITY_NAME = "rateAnalysisRaConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RaConfigurationService raConfigurationService;

    private final RaConfigurationRepository raConfigurationRepository;

    public RaConfigurationResource(RaConfigurationService raConfigurationService, RaConfigurationRepository raConfigurationRepository) {
        this.raConfigurationService = raConfigurationService;
        this.raConfigurationRepository = raConfigurationRepository;
    }

    /**
     * {@code POST  /ra-configurations} : Create a new raConfiguration.
     *
     * @param raConfigurationDTO the raConfigurationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new raConfigurationDTO, or with status {@code 400 (Bad Request)} if the raConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ra-configurations")
    public ResponseEntity<RaConfigurationDTO> createRaConfiguration(@Valid @RequestBody RaConfigurationDTO raConfigurationDTO)
        throws URISyntaxException {
        log.debug("REST request to save RaConfiguration : {}", raConfigurationDTO);
        if (raConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new raConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RaConfigurationDTO result = raConfigurationService.save(raConfigurationDTO);
        return ResponseEntity
            .created(new URI("/api/ra-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ra-configurations/:id} : Updates an existing raConfiguration.
     *
     * @param id the id of the raConfigurationDTO to save.
     * @param raConfigurationDTO the raConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated raConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the raConfigurationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the raConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ra-configurations/{id}")
    public ResponseEntity<RaConfigurationDTO> updateRaConfiguration(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RaConfigurationDTO raConfigurationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RaConfiguration : {}, {}", id, raConfigurationDTO);
        if (raConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, raConfigurationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!raConfigurationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RaConfigurationDTO result = raConfigurationService.save(raConfigurationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, raConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ra-configurations/:id} : Partial updates given fields of an existing raConfiguration, field will ignore if it is null
     *
     * @param id the id of the raConfigurationDTO to save.
     * @param raConfigurationDTO the raConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated raConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the raConfigurationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the raConfigurationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the raConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ra-configurations/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RaConfigurationDTO> partialUpdateRaConfiguration(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RaConfigurationDTO raConfigurationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RaConfiguration partially : {}, {}", id, raConfigurationDTO);
        if (raConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, raConfigurationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!raConfigurationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RaConfigurationDTO> result = raConfigurationService.partialUpdate(raConfigurationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, raConfigurationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ra-configurations} : get all the raConfigurations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of raConfigurations in body.
     */
    @GetMapping("/ra-configurations")
    public List<RaConfigurationDTO> getAllRaConfigurations() {
        log.debug("REST request to get all RaConfigurations");
        return raConfigurationService.findAll();
    }

    /**
     * {@code GET  /ra-configurations/:id} : get the "id" raConfiguration.
     *
     * @param id the id of the raConfigurationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the raConfigurationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ra-configurations/{id}")
    public ResponseEntity<RaConfigurationDTO> getRaConfiguration(@PathVariable Long id) {
        log.debug("REST request to get RaConfiguration : {}", id);
        Optional<RaConfigurationDTO> raConfigurationDTO = raConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(raConfigurationDTO);
    }

    /**
     * {@code DELETE  /ra-configurations/:id} : delete the "id" raConfiguration.
     *
     * @param id the id of the raConfigurationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ra-configurations/{id}")
    public ResponseEntity<Void> deleteRaConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete RaConfiguration : {}", id);
        raConfigurationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
