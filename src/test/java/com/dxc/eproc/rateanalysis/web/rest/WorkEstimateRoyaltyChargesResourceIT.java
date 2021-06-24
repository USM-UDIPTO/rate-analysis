package com.dxc.eproc.rateanalysis.web.rest;

import static com.dxc.eproc.rateanalysis.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.rateanalysis.IntegrationTest;
import com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyaltyCharges;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateRoyaltyChargesRepository;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRoyaltyChargesDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateRoyaltyChargesMapper;
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
 * Integration tests for the {@link WorkEstimateRoyaltyChargesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkEstimateRoyaltyChargesResourceIT {

    private static final Long DEFAULT_WORK_ESTIMATE_ID = 1L;
    private static final Long UPDATED_WORK_ESTIMATE_ID = 2L;

    private static final Long DEFAULT_CAT_WORK_SOR_ITEM_ID = 1L;
    private static final Long UPDATED_CAT_WORK_SOR_ITEM_ID = 2L;

    private static final Long DEFAULT_MATERIAL_MASTER_ID = 1L;
    private static final Long UPDATED_MATERIAL_MASTER_ID = 2L;

    private static final Long DEFAULT_SUB_ESTIMATE_ID = 1L;
    private static final Long UPDATED_SUB_ESTIMATE_ID = 2L;

    private static final Long DEFAULT_ROYALTY_RATE_MASTER_ID = 1L;
    private static final Long UPDATED_ROYALTY_RATE_MASTER_ID = 2L;

    private static final BigDecimal DEFAULT_SR_ROYALTY_CHARGES = new BigDecimal(1);
    private static final BigDecimal UPDATED_SR_ROYALTY_CHARGES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PREVAILING_ROYALTY_CHARGES = new BigDecimal(0);
    private static final BigDecimal UPDATED_PREVAILING_ROYALTY_CHARGES = new BigDecimal(1);

    private static final BigDecimal DEFAULT_DENSITY_FACTOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_DENSITY_FACTOR = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DIFFERENCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIFFERENCE = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/work-estimate-royalty-charges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkEstimateRoyaltyChargesRepository workEstimateRoyaltyChargesRepository;

    @Autowired
    private WorkEstimateRoyaltyChargesMapper workEstimateRoyaltyChargesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkEstimateRoyaltyChargesMockMvc;

    private WorkEstimateRoyaltyCharges workEstimateRoyaltyCharges;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateRoyaltyCharges createEntity(EntityManager em) {
        WorkEstimateRoyaltyCharges workEstimateRoyaltyCharges = new WorkEstimateRoyaltyCharges()
            .workEstimateId(DEFAULT_WORK_ESTIMATE_ID)
            .catWorkSorItemId(DEFAULT_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(DEFAULT_MATERIAL_MASTER_ID)
            .subEstimateId(DEFAULT_SUB_ESTIMATE_ID)
            .royaltyRateMasterId(DEFAULT_ROYALTY_RATE_MASTER_ID)
            .srRoyaltyCharges(DEFAULT_SR_ROYALTY_CHARGES)
            .prevailingRoyaltyCharges(DEFAULT_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(DEFAULT_DENSITY_FACTOR)
            .difference(DEFAULT_DIFFERENCE);
        return workEstimateRoyaltyCharges;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateRoyaltyCharges createUpdatedEntity(EntityManager em) {
        WorkEstimateRoyaltyCharges workEstimateRoyaltyCharges = new WorkEstimateRoyaltyCharges()
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .royaltyRateMasterId(UPDATED_ROYALTY_RATE_MASTER_ID)
            .srRoyaltyCharges(UPDATED_SR_ROYALTY_CHARGES)
            .prevailingRoyaltyCharges(UPDATED_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(UPDATED_DENSITY_FACTOR)
            .difference(UPDATED_DIFFERENCE);
        return workEstimateRoyaltyCharges;
    }

    @BeforeEach
    public void initTest() {
        workEstimateRoyaltyCharges = createEntity(em);
    }

    @Test
    @Transactional
    void createWorkEstimateRoyaltyCharges() throws Exception {
        int databaseSizeBeforeCreate = workEstimateRoyaltyChargesRepository.findAll().size();
        // Create the WorkEstimateRoyaltyCharges
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeCreate + 1);
        WorkEstimateRoyaltyCharges testWorkEstimateRoyaltyCharges = workEstimateRoyaltyChargesList.get(
            workEstimateRoyaltyChargesList.size() - 1
        );
        assertThat(testWorkEstimateRoyaltyCharges.getWorkEstimateId()).isEqualTo(DEFAULT_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getCatWorkSorItemId()).isEqualTo(DEFAULT_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getMaterialMasterId()).isEqualTo(DEFAULT_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getSubEstimateId()).isEqualTo(DEFAULT_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getRoyaltyRateMasterId()).isEqualTo(DEFAULT_ROYALTY_RATE_MASTER_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getSrRoyaltyCharges()).isEqualByComparingTo(DEFAULT_SR_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyaltyCharges.getPrevailingRoyaltyCharges()).isEqualByComparingTo(DEFAULT_PREVAILING_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyaltyCharges.getDensityFactor()).isEqualByComparingTo(DEFAULT_DENSITY_FACTOR);
        assertThat(testWorkEstimateRoyaltyCharges.getDifference()).isEqualByComparingTo(DEFAULT_DIFFERENCE);
    }

    @Test
    @Transactional
    void createWorkEstimateRoyaltyChargesWithExistingId() throws Exception {
        // Create the WorkEstimateRoyaltyCharges with an existing ID
        workEstimateRoyaltyCharges.setId(1L);
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        int databaseSizeBeforeCreate = workEstimateRoyaltyChargesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWorkEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyChargesRepository.findAll().size();
        // set the field null
        workEstimateRoyaltyCharges.setWorkEstimateId(null);

        // Create the WorkEstimateRoyaltyCharges, which fails.
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCatWorkSorItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyChargesRepository.findAll().size();
        // set the field null
        workEstimateRoyaltyCharges.setCatWorkSorItemId(null);

        // Create the WorkEstimateRoyaltyCharges, which fails.
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaterialMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyChargesRepository.findAll().size();
        // set the field null
        workEstimateRoyaltyCharges.setMaterialMasterId(null);

        // Create the WorkEstimateRoyaltyCharges, which fails.
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyChargesRepository.findAll().size();
        // set the field null
        workEstimateRoyaltyCharges.setSubEstimateId(null);

        // Create the WorkEstimateRoyaltyCharges, which fails.
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRoyaltyRateMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyChargesRepository.findAll().size();
        // set the field null
        workEstimateRoyaltyCharges.setRoyaltyRateMasterId(null);

        // Create the WorkEstimateRoyaltyCharges, which fails.
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSrRoyaltyChargesIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyChargesRepository.findAll().size();
        // set the field null
        workEstimateRoyaltyCharges.setSrRoyaltyCharges(null);

        // Create the WorkEstimateRoyaltyCharges, which fails.
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrevailingRoyaltyChargesIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyChargesRepository.findAll().size();
        // set the field null
        workEstimateRoyaltyCharges.setPrevailingRoyaltyCharges(null);

        // Create the WorkEstimateRoyaltyCharges, which fails.
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDensityFactorIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyChargesRepository.findAll().size();
        // set the field null
        workEstimateRoyaltyCharges.setDensityFactor(null);

        // Create the WorkEstimateRoyaltyCharges, which fails.
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllWorkEstimateRoyaltyCharges() throws Exception {
        // Initialize the database
        workEstimateRoyaltyChargesRepository.saveAndFlush(workEstimateRoyaltyCharges);

        // Get all the workEstimateRoyaltyChargesList
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workEstimateRoyaltyCharges.getId().intValue())))
            .andExpect(jsonPath("$.[*].workEstimateId").value(hasItem(DEFAULT_WORK_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].catWorkSorItemId").value(hasItem(DEFAULT_CAT_WORK_SOR_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].materialMasterId").value(hasItem(DEFAULT_MATERIAL_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].subEstimateId").value(hasItem(DEFAULT_SUB_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].royaltyRateMasterId").value(hasItem(DEFAULT_ROYALTY_RATE_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].srRoyaltyCharges").value(hasItem(sameNumber(DEFAULT_SR_ROYALTY_CHARGES))))
            .andExpect(jsonPath("$.[*].prevailingRoyaltyCharges").value(hasItem(sameNumber(DEFAULT_PREVAILING_ROYALTY_CHARGES))))
            .andExpect(jsonPath("$.[*].densityFactor").value(hasItem(sameNumber(DEFAULT_DENSITY_FACTOR))))
            .andExpect(jsonPath("$.[*].difference").value(hasItem(sameNumber(DEFAULT_DIFFERENCE))));
    }

    @Test
    @Transactional
    void getWorkEstimateRoyaltyCharges() throws Exception {
        // Initialize the database
        workEstimateRoyaltyChargesRepository.saveAndFlush(workEstimateRoyaltyCharges);

        // Get the workEstimateRoyaltyCharges
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(get(ENTITY_API_URL_ID, workEstimateRoyaltyCharges.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workEstimateRoyaltyCharges.getId().intValue()))
            .andExpect(jsonPath("$.workEstimateId").value(DEFAULT_WORK_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.catWorkSorItemId").value(DEFAULT_CAT_WORK_SOR_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.materialMasterId").value(DEFAULT_MATERIAL_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.subEstimateId").value(DEFAULT_SUB_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.royaltyRateMasterId").value(DEFAULT_ROYALTY_RATE_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.srRoyaltyCharges").value(sameNumber(DEFAULT_SR_ROYALTY_CHARGES)))
            .andExpect(jsonPath("$.prevailingRoyaltyCharges").value(sameNumber(DEFAULT_PREVAILING_ROYALTY_CHARGES)))
            .andExpect(jsonPath("$.densityFactor").value(sameNumber(DEFAULT_DENSITY_FACTOR)))
            .andExpect(jsonPath("$.difference").value(sameNumber(DEFAULT_DIFFERENCE)));
    }

    @Test
    @Transactional
    void getNonExistingWorkEstimateRoyaltyCharges() throws Exception {
        // Get the workEstimateRoyaltyCharges
        restWorkEstimateRoyaltyChargesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWorkEstimateRoyaltyCharges() throws Exception {
        // Initialize the database
        workEstimateRoyaltyChargesRepository.saveAndFlush(workEstimateRoyaltyCharges);

        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();

        // Update the workEstimateRoyaltyCharges
        WorkEstimateRoyaltyCharges updatedWorkEstimateRoyaltyCharges = workEstimateRoyaltyChargesRepository
            .findById(workEstimateRoyaltyCharges.getId())
            .get();
        // Disconnect from session so that the updates on updatedWorkEstimateRoyaltyCharges are not directly saved in db
        em.detach(updatedWorkEstimateRoyaltyCharges);
        updatedWorkEstimateRoyaltyCharges
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .royaltyRateMasterId(UPDATED_ROYALTY_RATE_MASTER_ID)
            .srRoyaltyCharges(UPDATED_SR_ROYALTY_CHARGES)
            .prevailingRoyaltyCharges(UPDATED_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(UPDATED_DENSITY_FACTOR)
            .difference(UPDATED_DIFFERENCE);
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(
            updatedWorkEstimateRoyaltyCharges
        );

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateRoyaltyChargesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRoyaltyCharges testWorkEstimateRoyaltyCharges = workEstimateRoyaltyChargesList.get(
            workEstimateRoyaltyChargesList.size() - 1
        );
        assertThat(testWorkEstimateRoyaltyCharges.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getCatWorkSorItemId()).isEqualTo(UPDATED_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getRoyaltyRateMasterId()).isEqualTo(UPDATED_ROYALTY_RATE_MASTER_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getSrRoyaltyCharges()).isEqualTo(UPDATED_SR_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyaltyCharges.getPrevailingRoyaltyCharges()).isEqualTo(UPDATED_PREVAILING_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyaltyCharges.getDensityFactor()).isEqualTo(UPDATED_DENSITY_FACTOR);
        assertThat(testWorkEstimateRoyaltyCharges.getDifference()).isEqualTo(UPDATED_DIFFERENCE);
    }

    @Test
    @Transactional
    void putNonExistingWorkEstimateRoyaltyCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();
        workEstimateRoyaltyCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyaltyCharges
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateRoyaltyChargesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkEstimateRoyaltyCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();
        workEstimateRoyaltyCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyaltyCharges
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkEstimateRoyaltyCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();
        workEstimateRoyaltyCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyaltyCharges
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkEstimateRoyaltyChargesWithPatch() throws Exception {
        // Initialize the database
        workEstimateRoyaltyChargesRepository.saveAndFlush(workEstimateRoyaltyCharges);

        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();

        // Update the workEstimateRoyaltyCharges using partial update
        WorkEstimateRoyaltyCharges partialUpdatedWorkEstimateRoyaltyCharges = new WorkEstimateRoyaltyCharges();
        partialUpdatedWorkEstimateRoyaltyCharges.setId(workEstimateRoyaltyCharges.getId());

        partialUpdatedWorkEstimateRoyaltyCharges
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .prevailingRoyaltyCharges(UPDATED_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(UPDATED_DENSITY_FACTOR);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateRoyaltyCharges.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateRoyaltyCharges))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRoyaltyCharges testWorkEstimateRoyaltyCharges = workEstimateRoyaltyChargesList.get(
            workEstimateRoyaltyChargesList.size() - 1
        );
        assertThat(testWorkEstimateRoyaltyCharges.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getCatWorkSorItemId()).isEqualTo(DEFAULT_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getSubEstimateId()).isEqualTo(DEFAULT_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getRoyaltyRateMasterId()).isEqualTo(DEFAULT_ROYALTY_RATE_MASTER_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getSrRoyaltyCharges()).isEqualByComparingTo(DEFAULT_SR_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyaltyCharges.getPrevailingRoyaltyCharges()).isEqualByComparingTo(UPDATED_PREVAILING_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyaltyCharges.getDensityFactor()).isEqualByComparingTo(UPDATED_DENSITY_FACTOR);
        assertThat(testWorkEstimateRoyaltyCharges.getDifference()).isEqualByComparingTo(DEFAULT_DIFFERENCE);
    }

    @Test
    @Transactional
    void fullUpdateWorkEstimateRoyaltyChargesWithPatch() throws Exception {
        // Initialize the database
        workEstimateRoyaltyChargesRepository.saveAndFlush(workEstimateRoyaltyCharges);

        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();

        // Update the workEstimateRoyaltyCharges using partial update
        WorkEstimateRoyaltyCharges partialUpdatedWorkEstimateRoyaltyCharges = new WorkEstimateRoyaltyCharges();
        partialUpdatedWorkEstimateRoyaltyCharges.setId(workEstimateRoyaltyCharges.getId());

        partialUpdatedWorkEstimateRoyaltyCharges
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .royaltyRateMasterId(UPDATED_ROYALTY_RATE_MASTER_ID)
            .srRoyaltyCharges(UPDATED_SR_ROYALTY_CHARGES)
            .prevailingRoyaltyCharges(UPDATED_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(UPDATED_DENSITY_FACTOR)
            .difference(UPDATED_DIFFERENCE);

        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateRoyaltyCharges.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateRoyaltyCharges))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRoyaltyCharges testWorkEstimateRoyaltyCharges = workEstimateRoyaltyChargesList.get(
            workEstimateRoyaltyChargesList.size() - 1
        );
        assertThat(testWorkEstimateRoyaltyCharges.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getCatWorkSorItemId()).isEqualTo(UPDATED_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getRoyaltyRateMasterId()).isEqualTo(UPDATED_ROYALTY_RATE_MASTER_ID);
        assertThat(testWorkEstimateRoyaltyCharges.getSrRoyaltyCharges()).isEqualByComparingTo(UPDATED_SR_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyaltyCharges.getPrevailingRoyaltyCharges()).isEqualByComparingTo(UPDATED_PREVAILING_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyaltyCharges.getDensityFactor()).isEqualByComparingTo(UPDATED_DENSITY_FACTOR);
        assertThat(testWorkEstimateRoyaltyCharges.getDifference()).isEqualByComparingTo(UPDATED_DIFFERENCE);
    }

    @Test
    @Transactional
    void patchNonExistingWorkEstimateRoyaltyCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();
        workEstimateRoyaltyCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyaltyCharges
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workEstimateRoyaltyChargesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkEstimateRoyaltyCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();
        workEstimateRoyaltyCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyaltyCharges
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkEstimateRoyaltyCharges() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyChargesRepository.findAll().size();
        workEstimateRoyaltyCharges.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyaltyCharges
        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyChargesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateRoyaltyCharges in the database
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkEstimateRoyaltyCharges() throws Exception {
        // Initialize the database
        workEstimateRoyaltyChargesRepository.saveAndFlush(workEstimateRoyaltyCharges);

        int databaseSizeBeforeDelete = workEstimateRoyaltyChargesRepository.findAll().size();

        // Delete the workEstimateRoyaltyCharges
        restWorkEstimateRoyaltyChargesMockMvc
            .perform(delete(ENTITY_API_URL_ID, workEstimateRoyaltyCharges.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkEstimateRoyaltyCharges> workEstimateRoyaltyChargesList = workEstimateRoyaltyChargesRepository.findAll();
        assertThat(workEstimateRoyaltyChargesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
