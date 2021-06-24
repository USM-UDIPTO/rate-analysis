package com.dxc.eproc.rateanalysis.web.rest;

import static com.dxc.eproc.rateanalysis.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.rateanalysis.IntegrationTest;
import com.dxc.eproc.rateanalysis.domain.WorkEstimateOtherAddnLiftCharges;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateOtherAddnLiftChargesRepository;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateOtherAddnLiftChargesDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateOtherAddnLiftChargesMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link WorkEstimateOtherAddnLiftChargesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkEstimateOtherAddnLiftChargesResourceIT {

    private static final Long DEFAULT_WORK_ESTIMATE_RATE_ANALYSIS_ID = 1L;
    private static final Long UPDATED_WORK_ESTIMATE_RATE_ANALYSIS_ID = 2L;

    private static final Long DEFAULT_NOTES_MASTER_ID = 1L;
    private static final Long UPDATED_NOTES_MASTER_ID = 2L;

    private static final Boolean DEFAULT_SELECTED = false;
    private static final Boolean UPDATED_SELECTED = true;

    private static final BigDecimal DEFAULT_ADDN_CHARGES = new BigDecimal(1);
    private static final BigDecimal UPDATED_ADDN_CHARGES = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/work-estimate-other-addn-lift-charges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkEstimateOtherAddnLiftChargesRepository workEstimateOtherAddnLiftChargesRepository;

    @Autowired
    private WorkEstimateOtherAddnLiftChargesMapper workEstimateOtherAddnLiftChargesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkEstimateOtherAddnLiftChargesMockMvc;

    private WorkEstimateOtherAddnLiftCharges workEstimateOtherAddnLiftCharges;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateOtherAddnLiftCharges createEntity(EntityManager em) {
        WorkEstimateOtherAddnLiftCharges workEstimateOtherAddnLiftCharges = new WorkEstimateOtherAddnLiftCharges()
            .workEstimateRateAnalysisId(DEFAULT_WORK_ESTIMATE_RATE_ANALYSIS_ID)
            .notesMasterId(DEFAULT_NOTES_MASTER_ID)
            .selected(DEFAULT_SELECTED)
            .addnCharges(DEFAULT_ADDN_CHARGES);
        return workEstimateOtherAddnLiftCharges;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateOtherAddnLiftCharges createUpdatedEntity(EntityManager em) {
        WorkEstimateOtherAddnLiftCharges workEstimateOtherAddnLiftCharges = new WorkEstimateOtherAddnLiftCharges()
            .workEstimateRateAnalysisId(UPDATED_WORK_ESTIMATE_RATE_ANALYSIS_ID)
            .notesMasterId(UPDATED_NOTES_MASTER_ID)
            .selected(UPDATED_SELECTED)
            .addnCharges(UPDATED_ADDN_CHARGES);
        return workEstimateOtherAddnLiftCharges;
    }

    @BeforeEach
    public void initTest() {
        workEstimateOtherAddnLiftCharges = createEntity(em);
    }

    @Test
    @Transactional
    void createWorkEstimateOtherAddnLiftCharges() throws Exception {
        int databaseSizeBeforeCreate = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        // Create the WorkEstimateOtherAddnLiftCharges
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeCreate + 1);
        WorkEstimateOtherAddnLiftCharges testWorkEstimateOtherAddnLiftCharges = workEstimateOtherAddnLiftChargesList.get(
            workEstimateOtherAddnLiftChargesList.size() - 1
        );
        assertThat(testWorkEstimateOtherAddnLiftCharges.getWorkEstimateRateAnalysisId()).isEqualTo(DEFAULT_WORK_ESTIMATE_RATE_ANALYSIS_ID);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getNotesMasterId()).isEqualTo(DEFAULT_NOTES_MASTER_ID);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getSelected()).isEqualTo(DEFAULT_SELECTED);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getAddnCharges()).isEqualByComparingTo(DEFAULT_ADDN_CHARGES);
    }

    @Test
    @Transactional
    void createWorkEstimateOtherAddnLiftChargesWithExistingId() throws Exception {
        // Create the WorkEstimateOtherAddnLiftCharges with an existing ID
        workEstimateOtherAddnLiftCharges.setId(1L);
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        int databaseSizeBeforeCreate = workEstimateOtherAddnLiftChargesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWorkEstimateRateAnalysisIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        // set the field null
        workEstimateOtherAddnLiftCharges.setWorkEstimateRateAnalysisId(null);

        // Create the WorkEstimateOtherAddnLiftCharges, which fails.
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNotesMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        // set the field null
        workEstimateOtherAddnLiftCharges.setNotesMasterId(null);

        // Create the WorkEstimateOtherAddnLiftCharges, which fails.
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSelectedIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        // set the field null
        workEstimateOtherAddnLiftCharges.setSelected(null);

        // Create the WorkEstimateOtherAddnLiftCharges, which fails.
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAddnChargesIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        // set the field null
        workEstimateOtherAddnLiftCharges.setAddnCharges(null);

        // Create the WorkEstimateOtherAddnLiftCharges, which fails.
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllWorkEstimateOtherAddnLiftCharges() throws Exception {
        // Initialize the database
        workEstimateOtherAddnLiftChargesRepository.saveAndFlush(workEstimateOtherAddnLiftCharges);

        // Get all the workEstimateOtherAddnLiftChargesList
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workEstimateOtherAddnLiftCharges.getId().intValue())))
            .andExpect(jsonPath("$.[*].workEstimateRateAnalysisId").value(hasItem(DEFAULT_WORK_ESTIMATE_RATE_ANALYSIS_ID.intValue())))
            .andExpect(jsonPath("$.[*].notesMasterId").value(hasItem(DEFAULT_NOTES_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].selected").value(hasItem(DEFAULT_SELECTED.booleanValue())))
            .andExpect(jsonPath("$.[*].addnCharges").value(hasItem(sameNumber(DEFAULT_ADDN_CHARGES))));
    }

    @Test
    @Transactional
    void getWorkEstimateOtherAddnLiftCharges() throws Exception {
        // Initialize the database
        workEstimateOtherAddnLiftChargesRepository.saveAndFlush(workEstimateOtherAddnLiftCharges);

        // Get the workEstimateOtherAddnLiftCharges
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(get(ENTITY_API_URL_ID, workEstimateOtherAddnLiftCharges.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workEstimateOtherAddnLiftCharges.getId().intValue()))
            .andExpect(jsonPath("$.workEstimateRateAnalysisId").value(DEFAULT_WORK_ESTIMATE_RATE_ANALYSIS_ID.intValue()))
            .andExpect(jsonPath("$.notesMasterId").value(DEFAULT_NOTES_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.selected").value(DEFAULT_SELECTED.booleanValue()))
            .andExpect(jsonPath("$.addnCharges").value(sameNumber(DEFAULT_ADDN_CHARGES)));
    }

    @Test
    @Transactional
    void getNonExistingWorkEstimateOtherAddnLiftCharges() throws Exception {
        // Get the workEstimateOtherAddnLiftCharges
        restWorkEstimateOtherAddnLiftChargesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWorkEstimateOtherAddnLiftCharges() throws Exception {
        // Initialize the database
        workEstimateOtherAddnLiftChargesRepository.saveAndFlush(workEstimateOtherAddnLiftCharges);

        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();

        // Update the workEstimateOtherAddnLiftCharges
        WorkEstimateOtherAddnLiftCharges updatedWorkEstimateOtherAddnLiftCharges = workEstimateOtherAddnLiftChargesRepository
            .findById(workEstimateOtherAddnLiftCharges.getId())
            .get();
        // Disconnect from session so that the updates on updatedWorkEstimateOtherAddnLiftCharges are not directly saved in db
        em.detach(updatedWorkEstimateOtherAddnLiftCharges);
        updatedWorkEstimateOtherAddnLiftCharges
            .workEstimateRateAnalysisId(UPDATED_WORK_ESTIMATE_RATE_ANALYSIS_ID)
            .notesMasterId(UPDATED_NOTES_MASTER_ID)
            .selected(UPDATED_SELECTED)
            .addnCharges(UPDATED_ADDN_CHARGES);
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            updatedWorkEstimateOtherAddnLiftCharges
        );

        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateOtherAddnLiftChargesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateOtherAddnLiftCharges testWorkEstimateOtherAddnLiftCharges = workEstimateOtherAddnLiftChargesList.get(
            workEstimateOtherAddnLiftChargesList.size() - 1
        );
        assertThat(testWorkEstimateOtherAddnLiftCharges.getWorkEstimateRateAnalysisId()).isEqualTo(UPDATED_WORK_ESTIMATE_RATE_ANALYSIS_ID);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getNotesMasterId()).isEqualTo(UPDATED_NOTES_MASTER_ID);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getSelected()).isEqualTo(UPDATED_SELECTED);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getAddnCharges()).isEqualTo(UPDATED_ADDN_CHARGES);
    }

    @Test
    @Transactional
    void putNonExistingWorkEstimateOtherAddnLiftCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        workEstimateOtherAddnLiftCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateOtherAddnLiftCharges
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateOtherAddnLiftChargesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkEstimateOtherAddnLiftCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        workEstimateOtherAddnLiftCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateOtherAddnLiftCharges
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkEstimateOtherAddnLiftCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        workEstimateOtherAddnLiftCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateOtherAddnLiftCharges
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkEstimateOtherAddnLiftChargesWithPatch() throws Exception {
        // Initialize the database
        workEstimateOtherAddnLiftChargesRepository.saveAndFlush(workEstimateOtherAddnLiftCharges);

        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();

        // Update the workEstimateOtherAddnLiftCharges using partial update
        WorkEstimateOtherAddnLiftCharges partialUpdatedWorkEstimateOtherAddnLiftCharges = new WorkEstimateOtherAddnLiftCharges();
        partialUpdatedWorkEstimateOtherAddnLiftCharges.setId(workEstimateOtherAddnLiftCharges.getId());

        partialUpdatedWorkEstimateOtherAddnLiftCharges.selected(UPDATED_SELECTED).addnCharges(UPDATED_ADDN_CHARGES);

        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateOtherAddnLiftCharges.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateOtherAddnLiftCharges))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateOtherAddnLiftCharges testWorkEstimateOtherAddnLiftCharges = workEstimateOtherAddnLiftChargesList.get(
            workEstimateOtherAddnLiftChargesList.size() - 1
        );
        assertThat(testWorkEstimateOtherAddnLiftCharges.getWorkEstimateRateAnalysisId()).isEqualTo(DEFAULT_WORK_ESTIMATE_RATE_ANALYSIS_ID);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getNotesMasterId()).isEqualTo(DEFAULT_NOTES_MASTER_ID);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getSelected()).isEqualTo(UPDATED_SELECTED);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getAddnCharges()).isEqualByComparingTo(UPDATED_ADDN_CHARGES);
    }

    @Test
    @Transactional
    void fullUpdateWorkEstimateOtherAddnLiftChargesWithPatch() throws Exception {
        // Initialize the database
        workEstimateOtherAddnLiftChargesRepository.saveAndFlush(workEstimateOtherAddnLiftCharges);

        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();

        // Update the workEstimateOtherAddnLiftCharges using partial update
        WorkEstimateOtherAddnLiftCharges partialUpdatedWorkEstimateOtherAddnLiftCharges = new WorkEstimateOtherAddnLiftCharges();
        partialUpdatedWorkEstimateOtherAddnLiftCharges.setId(workEstimateOtherAddnLiftCharges.getId());

        partialUpdatedWorkEstimateOtherAddnLiftCharges
            .workEstimateRateAnalysisId(UPDATED_WORK_ESTIMATE_RATE_ANALYSIS_ID)
            .notesMasterId(UPDATED_NOTES_MASTER_ID)
            .selected(UPDATED_SELECTED)
            .addnCharges(UPDATED_ADDN_CHARGES);

        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateOtherAddnLiftCharges.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateOtherAddnLiftCharges))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateOtherAddnLiftCharges testWorkEstimateOtherAddnLiftCharges = workEstimateOtherAddnLiftChargesList.get(
            workEstimateOtherAddnLiftChargesList.size() - 1
        );
        assertThat(testWorkEstimateOtherAddnLiftCharges.getWorkEstimateRateAnalysisId()).isEqualTo(UPDATED_WORK_ESTIMATE_RATE_ANALYSIS_ID);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getNotesMasterId()).isEqualTo(UPDATED_NOTES_MASTER_ID);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getSelected()).isEqualTo(UPDATED_SELECTED);
        assertThat(testWorkEstimateOtherAddnLiftCharges.getAddnCharges()).isEqualByComparingTo(UPDATED_ADDN_CHARGES);
    }

    @Test
    @Transactional
    void patchNonExistingWorkEstimateOtherAddnLiftCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        workEstimateOtherAddnLiftCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateOtherAddnLiftCharges
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workEstimateOtherAddnLiftChargesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkEstimateOtherAddnLiftCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        workEstimateOtherAddnLiftCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateOtherAddnLiftCharges
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkEstimateOtherAddnLiftCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateOtherAddnLiftChargesRepository.findAll().size();
        workEstimateOtherAddnLiftCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateOtherAddnLiftCharges
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = workEstimateOtherAddnLiftChargesMapper.toDto(
            workEstimateOtherAddnLiftCharges
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateOtherAddnLiftChargesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateOtherAddnLiftCharges in the database
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkEstimateOtherAddnLiftCharges() throws Exception {
        // Initialize the database
        workEstimateOtherAddnLiftChargesRepository.saveAndFlush(workEstimateOtherAddnLiftCharges);

        int databaseSizeBeforeDelete = workEstimateOtherAddnLiftChargesRepository.findAll().size();

        // Delete the workEstimateOtherAddnLiftCharges
        restWorkEstimateOtherAddnLiftChargesMockMvc
            .perform(delete(ENTITY_API_URL_ID, workEstimateOtherAddnLiftCharges.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkEstimateOtherAddnLiftCharges> workEstimateOtherAddnLiftChargesList = workEstimateOtherAddnLiftChargesRepository.findAll();
        assertThat(workEstimateOtherAddnLiftChargesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
