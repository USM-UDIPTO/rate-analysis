package com.dxc.eproc.rateanalysis.web.rest;

import static com.dxc.eproc.rateanalysis.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.rateanalysis.IntegrationTest;
import com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyalty;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateRoyaltyRepository;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRoyaltyDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateRoyaltyMapper;
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
 * Integration tests for the {@link WorkEstimateRoyaltyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkEstimateRoyaltyResourceIT {

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

    private static final String ENTITY_API_URL = "/api/work-estimate-royalties";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkEstimateRoyaltyRepository workEstimateRoyaltyRepository;

    @Autowired
    private WorkEstimateRoyaltyMapper workEstimateRoyaltyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkEstimateRoyaltyMockMvc;

    private WorkEstimateRoyalty workEstimateRoyalty;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateRoyalty createEntity(EntityManager em) {
        WorkEstimateRoyalty workEstimateRoyalty = new WorkEstimateRoyalty()
            .workEstimateId(DEFAULT_WORK_ESTIMATE_ID)
            .catWorkSorItemId(DEFAULT_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(DEFAULT_MATERIAL_MASTER_ID)
            .subEstimateId(DEFAULT_SUB_ESTIMATE_ID)
            .royaltyRateMasterId(DEFAULT_ROYALTY_RATE_MASTER_ID)
            .srRoyaltyCharges(DEFAULT_SR_ROYALTY_CHARGES)
            .prevailingRoyaltyCharges(DEFAULT_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(DEFAULT_DENSITY_FACTOR)
            .difference(DEFAULT_DIFFERENCE);
        return workEstimateRoyalty;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateRoyalty createUpdatedEntity(EntityManager em) {
        WorkEstimateRoyalty workEstimateRoyalty = new WorkEstimateRoyalty()
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .royaltyRateMasterId(UPDATED_ROYALTY_RATE_MASTER_ID)
            .srRoyaltyCharges(UPDATED_SR_ROYALTY_CHARGES)
            .prevailingRoyaltyCharges(UPDATED_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(UPDATED_DENSITY_FACTOR)
            .difference(UPDATED_DIFFERENCE);
        return workEstimateRoyalty;
    }

    @BeforeEach
    public void initTest() {
        workEstimateRoyalty = createEntity(em);
    }

    @Test
    @Transactional
    void createWorkEstimateRoyalty() throws Exception {
        int databaseSizeBeforeCreate = workEstimateRoyaltyRepository.findAll().size();
        // Create the WorkEstimateRoyalty
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);
        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeCreate + 1);
        WorkEstimateRoyalty testWorkEstimateRoyalty = workEstimateRoyaltyList.get(workEstimateRoyaltyList.size() - 1);
        assertThat(testWorkEstimateRoyalty.getWorkEstimateId()).isEqualTo(DEFAULT_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyalty.getCatWorkSorItemId()).isEqualTo(DEFAULT_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateRoyalty.getMaterialMasterId()).isEqualTo(DEFAULT_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateRoyalty.getSubEstimateId()).isEqualTo(DEFAULT_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyalty.getRoyaltyRateMasterId()).isEqualTo(DEFAULT_ROYALTY_RATE_MASTER_ID);
        assertThat(testWorkEstimateRoyalty.getSrRoyaltyCharges()).isEqualByComparingTo(DEFAULT_SR_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyalty.getPrevailingRoyaltyCharges()).isEqualByComparingTo(DEFAULT_PREVAILING_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyalty.getDensityFactor()).isEqualByComparingTo(DEFAULT_DENSITY_FACTOR);
        assertThat(testWorkEstimateRoyalty.getDifference()).isEqualByComparingTo(DEFAULT_DIFFERENCE);
    }

    @Test
    @Transactional
    void createWorkEstimateRoyaltyWithExistingId() throws Exception {
        // Create the WorkEstimateRoyalty with an existing ID
        workEstimateRoyalty.setId(1L);
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        int databaseSizeBeforeCreate = workEstimateRoyaltyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWorkEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyRepository.findAll().size();
        // set the field null
        workEstimateRoyalty.setWorkEstimateId(null);

        // Create the WorkEstimateRoyalty, which fails.
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCatWorkSorItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyRepository.findAll().size();
        // set the field null
        workEstimateRoyalty.setCatWorkSorItemId(null);

        // Create the WorkEstimateRoyalty, which fails.
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaterialMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyRepository.findAll().size();
        // set the field null
        workEstimateRoyalty.setMaterialMasterId(null);

        // Create the WorkEstimateRoyalty, which fails.
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyRepository.findAll().size();
        // set the field null
        workEstimateRoyalty.setSubEstimateId(null);

        // Create the WorkEstimateRoyalty, which fails.
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRoyaltyRateMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyRepository.findAll().size();
        // set the field null
        workEstimateRoyalty.setRoyaltyRateMasterId(null);

        // Create the WorkEstimateRoyalty, which fails.
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSrRoyaltyChargesIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyRepository.findAll().size();
        // set the field null
        workEstimateRoyalty.setSrRoyaltyCharges(null);

        // Create the WorkEstimateRoyalty, which fails.
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrevailingRoyaltyChargesIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyRepository.findAll().size();
        // set the field null
        workEstimateRoyalty.setPrevailingRoyaltyCharges(null);

        // Create the WorkEstimateRoyalty, which fails.
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDensityFactorIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRoyaltyRepository.findAll().size();
        // set the field null
        workEstimateRoyalty.setDensityFactor(null);

        // Create the WorkEstimateRoyalty, which fails.
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllWorkEstimateRoyalties() throws Exception {
        // Initialize the database
        workEstimateRoyaltyRepository.saveAndFlush(workEstimateRoyalty);

        // Get all the workEstimateRoyaltyList
        restWorkEstimateRoyaltyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workEstimateRoyalty.getId().intValue())))
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
    void getWorkEstimateRoyalty() throws Exception {
        // Initialize the database
        workEstimateRoyaltyRepository.saveAndFlush(workEstimateRoyalty);

        // Get the workEstimateRoyalty
        restWorkEstimateRoyaltyMockMvc
            .perform(get(ENTITY_API_URL_ID, workEstimateRoyalty.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workEstimateRoyalty.getId().intValue()))
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
    void getNonExistingWorkEstimateRoyalty() throws Exception {
        // Get the workEstimateRoyalty
        restWorkEstimateRoyaltyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWorkEstimateRoyalty() throws Exception {
        // Initialize the database
        workEstimateRoyaltyRepository.saveAndFlush(workEstimateRoyalty);

        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();

        // Update the workEstimateRoyalty
        WorkEstimateRoyalty updatedWorkEstimateRoyalty = workEstimateRoyaltyRepository.findById(workEstimateRoyalty.getId()).get();
        // Disconnect from session so that the updates on updatedWorkEstimateRoyalty are not directly saved in db
        em.detach(updatedWorkEstimateRoyalty);
        updatedWorkEstimateRoyalty
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .royaltyRateMasterId(UPDATED_ROYALTY_RATE_MASTER_ID)
            .srRoyaltyCharges(UPDATED_SR_ROYALTY_CHARGES)
            .prevailingRoyaltyCharges(UPDATED_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(UPDATED_DENSITY_FACTOR)
            .difference(UPDATED_DIFFERENCE);
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(updatedWorkEstimateRoyalty);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateRoyaltyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRoyalty testWorkEstimateRoyalty = workEstimateRoyaltyList.get(workEstimateRoyaltyList.size() - 1);
        assertThat(testWorkEstimateRoyalty.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyalty.getCatWorkSorItemId()).isEqualTo(UPDATED_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateRoyalty.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateRoyalty.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyalty.getRoyaltyRateMasterId()).isEqualTo(UPDATED_ROYALTY_RATE_MASTER_ID);
        assertThat(testWorkEstimateRoyalty.getSrRoyaltyCharges()).isEqualTo(UPDATED_SR_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyalty.getPrevailingRoyaltyCharges()).isEqualTo(UPDATED_PREVAILING_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyalty.getDensityFactor()).isEqualTo(UPDATED_DENSITY_FACTOR);
        assertThat(testWorkEstimateRoyalty.getDifference()).isEqualTo(UPDATED_DIFFERENCE);
    }

    @Test
    @Transactional
    void putNonExistingWorkEstimateRoyalty() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();
        workEstimateRoyalty.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyalty
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateRoyaltyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkEstimateRoyalty() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();
        workEstimateRoyalty.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyalty
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkEstimateRoyalty() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();
        workEstimateRoyalty.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyalty
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkEstimateRoyaltyWithPatch() throws Exception {
        // Initialize the database
        workEstimateRoyaltyRepository.saveAndFlush(workEstimateRoyalty);

        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();

        // Update the workEstimateRoyalty using partial update
        WorkEstimateRoyalty partialUpdatedWorkEstimateRoyalty = new WorkEstimateRoyalty();
        partialUpdatedWorkEstimateRoyalty.setId(workEstimateRoyalty.getId());

        partialUpdatedWorkEstimateRoyalty
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .densityFactor(UPDATED_DENSITY_FACTOR);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateRoyalty.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateRoyalty))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRoyalty testWorkEstimateRoyalty = workEstimateRoyaltyList.get(workEstimateRoyaltyList.size() - 1);
        assertThat(testWorkEstimateRoyalty.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyalty.getCatWorkSorItemId()).isEqualTo(DEFAULT_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateRoyalty.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateRoyalty.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyalty.getRoyaltyRateMasterId()).isEqualTo(DEFAULT_ROYALTY_RATE_MASTER_ID);
        assertThat(testWorkEstimateRoyalty.getSrRoyaltyCharges()).isEqualByComparingTo(DEFAULT_SR_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyalty.getPrevailingRoyaltyCharges()).isEqualByComparingTo(DEFAULT_PREVAILING_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyalty.getDensityFactor()).isEqualByComparingTo(UPDATED_DENSITY_FACTOR);
        assertThat(testWorkEstimateRoyalty.getDifference()).isEqualByComparingTo(DEFAULT_DIFFERENCE);
    }

    @Test
    @Transactional
    void fullUpdateWorkEstimateRoyaltyWithPatch() throws Exception {
        // Initialize the database
        workEstimateRoyaltyRepository.saveAndFlush(workEstimateRoyalty);

        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();

        // Update the workEstimateRoyalty using partial update
        WorkEstimateRoyalty partialUpdatedWorkEstimateRoyalty = new WorkEstimateRoyalty();
        partialUpdatedWorkEstimateRoyalty.setId(workEstimateRoyalty.getId());

        partialUpdatedWorkEstimateRoyalty
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .royaltyRateMasterId(UPDATED_ROYALTY_RATE_MASTER_ID)
            .srRoyaltyCharges(UPDATED_SR_ROYALTY_CHARGES)
            .prevailingRoyaltyCharges(UPDATED_PREVAILING_ROYALTY_CHARGES)
            .densityFactor(UPDATED_DENSITY_FACTOR)
            .difference(UPDATED_DIFFERENCE);

        restWorkEstimateRoyaltyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateRoyalty.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateRoyalty))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRoyalty testWorkEstimateRoyalty = workEstimateRoyaltyList.get(workEstimateRoyaltyList.size() - 1);
        assertThat(testWorkEstimateRoyalty.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyalty.getCatWorkSorItemId()).isEqualTo(UPDATED_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateRoyalty.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateRoyalty.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateRoyalty.getRoyaltyRateMasterId()).isEqualTo(UPDATED_ROYALTY_RATE_MASTER_ID);
        assertThat(testWorkEstimateRoyalty.getSrRoyaltyCharges()).isEqualByComparingTo(UPDATED_SR_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyalty.getPrevailingRoyaltyCharges()).isEqualByComparingTo(UPDATED_PREVAILING_ROYALTY_CHARGES);
        assertThat(testWorkEstimateRoyalty.getDensityFactor()).isEqualByComparingTo(UPDATED_DENSITY_FACTOR);
        assertThat(testWorkEstimateRoyalty.getDifference()).isEqualByComparingTo(UPDATED_DIFFERENCE);
    }

    @Test
    @Transactional
    void patchNonExistingWorkEstimateRoyalty() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();
        workEstimateRoyalty.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyalty
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workEstimateRoyaltyDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkEstimateRoyalty() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();
        workEstimateRoyalty.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyalty
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkEstimateRoyalty() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRoyaltyRepository.findAll().size();
        workEstimateRoyalty.setId(count.incrementAndGet());

        // Create the WorkEstimateRoyalty
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO = workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRoyaltyMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRoyaltyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateRoyalty in the database
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkEstimateRoyalty() throws Exception {
        // Initialize the database
        workEstimateRoyaltyRepository.saveAndFlush(workEstimateRoyalty);

        int databaseSizeBeforeDelete = workEstimateRoyaltyRepository.findAll().size();

        // Delete the workEstimateRoyalty
        restWorkEstimateRoyaltyMockMvc
            .perform(delete(ENTITY_API_URL_ID, workEstimateRoyalty.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkEstimateRoyalty> workEstimateRoyaltyList = workEstimateRoyaltyRepository.findAll();
        assertThat(workEstimateRoyaltyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
