package com.dxc.eproc.rateanalysis.web.rest;

import static com.dxc.eproc.rateanalysis.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.rateanalysis.IntegrationTest;
import com.dxc.eproc.rateanalysis.domain.WorkEstimateMarketRate;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateMarketRateRepository;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateMarketRateDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateMarketRateMapper;
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
 * Integration tests for the {@link WorkEstimateMarketRateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkEstimateMarketRateResourceIT {

    private static final Long DEFAULT_WORK_ESTIMATE_ID = 1L;
    private static final Long UPDATED_WORK_ESTIMATE_ID = 2L;

    private static final Long DEFAULT_SUB_ESTIMATE_ID = 1L;
    private static final Long UPDATED_SUB_ESTIMATE_ID = 2L;

    private static final Long DEFAULT_MATERIAL_MASTER_ID = 1L;
    private static final Long UPDATED_MATERIAL_MASTER_ID = 2L;

    private static final BigDecimal DEFAULT_DIFFERENCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIFFERENCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PREVAILING_MARKET_RATE = new BigDecimal(0);
    private static final BigDecimal UPDATED_PREVAILING_MARKET_RATE = new BigDecimal(1);

    private static final String ENTITY_API_URL = "/api/work-estimate-market-rates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkEstimateMarketRateRepository workEstimateMarketRateRepository;

    @Autowired
    private WorkEstimateMarketRateMapper workEstimateMarketRateMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkEstimateMarketRateMockMvc;

    private WorkEstimateMarketRate workEstimateMarketRate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateMarketRate createEntity(EntityManager em) {
        WorkEstimateMarketRate workEstimateMarketRate = new WorkEstimateMarketRate()
            .workEstimateId(DEFAULT_WORK_ESTIMATE_ID)
            .subEstimateId(DEFAULT_SUB_ESTIMATE_ID)
            .materialMasterId(DEFAULT_MATERIAL_MASTER_ID)
            .difference(DEFAULT_DIFFERENCE)
            .prevailingMarketRate(DEFAULT_PREVAILING_MARKET_RATE);
        return workEstimateMarketRate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateMarketRate createUpdatedEntity(EntityManager em) {
        WorkEstimateMarketRate workEstimateMarketRate = new WorkEstimateMarketRate()
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .difference(UPDATED_DIFFERENCE)
            .prevailingMarketRate(UPDATED_PREVAILING_MARKET_RATE);
        return workEstimateMarketRate;
    }

    @BeforeEach
    public void initTest() {
        workEstimateMarketRate = createEntity(em);
    }

    @Test
    @Transactional
    void createWorkEstimateMarketRate() throws Exception {
        int databaseSizeBeforeCreate = workEstimateMarketRateRepository.findAll().size();
        // Create the WorkEstimateMarketRate
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);
        restWorkEstimateMarketRateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeCreate + 1);
        WorkEstimateMarketRate testWorkEstimateMarketRate = workEstimateMarketRateList.get(workEstimateMarketRateList.size() - 1);
        assertThat(testWorkEstimateMarketRate.getWorkEstimateId()).isEqualTo(DEFAULT_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateMarketRate.getSubEstimateId()).isEqualTo(DEFAULT_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateMarketRate.getMaterialMasterId()).isEqualTo(DEFAULT_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateMarketRate.getDifference()).isEqualByComparingTo(DEFAULT_DIFFERENCE);
        assertThat(testWorkEstimateMarketRate.getPrevailingMarketRate()).isEqualByComparingTo(DEFAULT_PREVAILING_MARKET_RATE);
    }

    @Test
    @Transactional
    void createWorkEstimateMarketRateWithExistingId() throws Exception {
        // Create the WorkEstimateMarketRate with an existing ID
        workEstimateMarketRate.setId(1L);
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        int databaseSizeBeforeCreate = workEstimateMarketRateRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkEstimateMarketRateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWorkEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateMarketRateRepository.findAll().size();
        // set the field null
        workEstimateMarketRate.setWorkEstimateId(null);

        // Create the WorkEstimateMarketRate, which fails.
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        restWorkEstimateMarketRateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateMarketRateRepository.findAll().size();
        // set the field null
        workEstimateMarketRate.setSubEstimateId(null);

        // Create the WorkEstimateMarketRate, which fails.
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        restWorkEstimateMarketRateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaterialMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateMarketRateRepository.findAll().size();
        // set the field null
        workEstimateMarketRate.setMaterialMasterId(null);

        // Create the WorkEstimateMarketRate, which fails.
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        restWorkEstimateMarketRateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDifferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateMarketRateRepository.findAll().size();
        // set the field null
        workEstimateMarketRate.setDifference(null);

        // Create the WorkEstimateMarketRate, which fails.
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        restWorkEstimateMarketRateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrevailingMarketRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateMarketRateRepository.findAll().size();
        // set the field null
        workEstimateMarketRate.setPrevailingMarketRate(null);

        // Create the WorkEstimateMarketRate, which fails.
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        restWorkEstimateMarketRateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllWorkEstimateMarketRates() throws Exception {
        // Initialize the database
        workEstimateMarketRateRepository.saveAndFlush(workEstimateMarketRate);

        // Get all the workEstimateMarketRateList
        restWorkEstimateMarketRateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workEstimateMarketRate.getId().intValue())))
            .andExpect(jsonPath("$.[*].workEstimateId").value(hasItem(DEFAULT_WORK_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].subEstimateId").value(hasItem(DEFAULT_SUB_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].materialMasterId").value(hasItem(DEFAULT_MATERIAL_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].difference").value(hasItem(sameNumber(DEFAULT_DIFFERENCE))))
            .andExpect(jsonPath("$.[*].prevailingMarketRate").value(hasItem(sameNumber(DEFAULT_PREVAILING_MARKET_RATE))));
    }

    @Test
    @Transactional
    void getWorkEstimateMarketRate() throws Exception {
        // Initialize the database
        workEstimateMarketRateRepository.saveAndFlush(workEstimateMarketRate);

        // Get the workEstimateMarketRate
        restWorkEstimateMarketRateMockMvc
            .perform(get(ENTITY_API_URL_ID, workEstimateMarketRate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workEstimateMarketRate.getId().intValue()))
            .andExpect(jsonPath("$.workEstimateId").value(DEFAULT_WORK_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.subEstimateId").value(DEFAULT_SUB_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.materialMasterId").value(DEFAULT_MATERIAL_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.difference").value(sameNumber(DEFAULT_DIFFERENCE)))
            .andExpect(jsonPath("$.prevailingMarketRate").value(sameNumber(DEFAULT_PREVAILING_MARKET_RATE)));
    }

    @Test
    @Transactional
    void getNonExistingWorkEstimateMarketRate() throws Exception {
        // Get the workEstimateMarketRate
        restWorkEstimateMarketRateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWorkEstimateMarketRate() throws Exception {
        // Initialize the database
        workEstimateMarketRateRepository.saveAndFlush(workEstimateMarketRate);

        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();

        // Update the workEstimateMarketRate
        WorkEstimateMarketRate updatedWorkEstimateMarketRate = workEstimateMarketRateRepository
            .findById(workEstimateMarketRate.getId())
            .get();
        // Disconnect from session so that the updates on updatedWorkEstimateMarketRate are not directly saved in db
        em.detach(updatedWorkEstimateMarketRate);
        updatedWorkEstimateMarketRate
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .difference(UPDATED_DIFFERENCE)
            .prevailingMarketRate(UPDATED_PREVAILING_MARKET_RATE);
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(updatedWorkEstimateMarketRate);

        restWorkEstimateMarketRateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateMarketRateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateMarketRate testWorkEstimateMarketRate = workEstimateMarketRateList.get(workEstimateMarketRateList.size() - 1);
        assertThat(testWorkEstimateMarketRate.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateMarketRate.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateMarketRate.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateMarketRate.getDifference()).isEqualTo(UPDATED_DIFFERENCE);
        assertThat(testWorkEstimateMarketRate.getPrevailingMarketRate()).isEqualTo(UPDATED_PREVAILING_MARKET_RATE);
    }

    @Test
    @Transactional
    void putNonExistingWorkEstimateMarketRate() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();
        workEstimateMarketRate.setId(count.incrementAndGet());

        // Create the WorkEstimateMarketRate
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateMarketRateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateMarketRateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkEstimateMarketRate() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();
        workEstimateMarketRate.setId(count.incrementAndGet());

        // Create the WorkEstimateMarketRate
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateMarketRateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkEstimateMarketRate() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();
        workEstimateMarketRate.setId(count.incrementAndGet());

        // Create the WorkEstimateMarketRate
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateMarketRateMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkEstimateMarketRateWithPatch() throws Exception {
        // Initialize the database
        workEstimateMarketRateRepository.saveAndFlush(workEstimateMarketRate);

        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();

        // Update the workEstimateMarketRate using partial update
        WorkEstimateMarketRate partialUpdatedWorkEstimateMarketRate = new WorkEstimateMarketRate();
        partialUpdatedWorkEstimateMarketRate.setId(workEstimateMarketRate.getId());

        partialUpdatedWorkEstimateMarketRate.workEstimateId(UPDATED_WORK_ESTIMATE_ID).prevailingMarketRate(UPDATED_PREVAILING_MARKET_RATE);

        restWorkEstimateMarketRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateMarketRate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateMarketRate))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateMarketRate testWorkEstimateMarketRate = workEstimateMarketRateList.get(workEstimateMarketRateList.size() - 1);
        assertThat(testWorkEstimateMarketRate.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateMarketRate.getSubEstimateId()).isEqualTo(DEFAULT_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateMarketRate.getMaterialMasterId()).isEqualTo(DEFAULT_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateMarketRate.getDifference()).isEqualByComparingTo(DEFAULT_DIFFERENCE);
        assertThat(testWorkEstimateMarketRate.getPrevailingMarketRate()).isEqualByComparingTo(UPDATED_PREVAILING_MARKET_RATE);
    }

    @Test
    @Transactional
    void fullUpdateWorkEstimateMarketRateWithPatch() throws Exception {
        // Initialize the database
        workEstimateMarketRateRepository.saveAndFlush(workEstimateMarketRate);

        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();

        // Update the workEstimateMarketRate using partial update
        WorkEstimateMarketRate partialUpdatedWorkEstimateMarketRate = new WorkEstimateMarketRate();
        partialUpdatedWorkEstimateMarketRate.setId(workEstimateMarketRate.getId());

        partialUpdatedWorkEstimateMarketRate
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .difference(UPDATED_DIFFERENCE)
            .prevailingMarketRate(UPDATED_PREVAILING_MARKET_RATE);

        restWorkEstimateMarketRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateMarketRate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateMarketRate))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateMarketRate testWorkEstimateMarketRate = workEstimateMarketRateList.get(workEstimateMarketRateList.size() - 1);
        assertThat(testWorkEstimateMarketRate.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateMarketRate.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateMarketRate.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateMarketRate.getDifference()).isEqualByComparingTo(UPDATED_DIFFERENCE);
        assertThat(testWorkEstimateMarketRate.getPrevailingMarketRate()).isEqualByComparingTo(UPDATED_PREVAILING_MARKET_RATE);
    }

    @Test
    @Transactional
    void patchNonExistingWorkEstimateMarketRate() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();
        workEstimateMarketRate.setId(count.incrementAndGet());

        // Create the WorkEstimateMarketRate
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateMarketRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workEstimateMarketRateDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkEstimateMarketRate() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();
        workEstimateMarketRate.setId(count.incrementAndGet());

        // Create the WorkEstimateMarketRate
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateMarketRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkEstimateMarketRate() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateMarketRateRepository.findAll().size();
        workEstimateMarketRate.setId(count.incrementAndGet());

        // Create the WorkEstimateMarketRate
        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = workEstimateMarketRateMapper.toDto(workEstimateMarketRate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateMarketRateMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateMarketRateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateMarketRate in the database
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkEstimateMarketRate() throws Exception {
        // Initialize the database
        workEstimateMarketRateRepository.saveAndFlush(workEstimateMarketRate);

        int databaseSizeBeforeDelete = workEstimateMarketRateRepository.findAll().size();

        // Delete the workEstimateMarketRate
        restWorkEstimateMarketRateMockMvc
            .perform(delete(ENTITY_API_URL_ID, workEstimateMarketRate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkEstimateMarketRate> workEstimateMarketRateList = workEstimateMarketRateRepository.findAll();
        assertThat(workEstimateMarketRateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
