package com.dxc.eproc.rateanalysis.web.rest;

import static com.dxc.eproc.rateanalysis.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.rateanalysis.IntegrationTest;
import com.dxc.eproc.rateanalysis.domain.WorkEstimateLead;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateLeadRepository;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLeadDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateLeadMapper;
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
 * Integration tests for the {@link WorkEstimateLeadResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkEstimateLeadResourceIT {

    private static final Long DEFAULT_WORK_ESTIMATE_ID = 1L;
    private static final Long UPDATED_WORK_ESTIMATE_ID = 2L;

    private static final Long DEFAULT_CAT_WORK_SOR_ITEM_ID = 1L;
    private static final Long UPDATED_CAT_WORK_SOR_ITEM_ID = 2L;

    private static final Long DEFAULT_MATERIAL_MASTER_ID = 1L;
    private static final Long UPDATED_MATERIAL_MASTER_ID = 2L;

    private static final Long DEFAULT_NHWY_ROAD_TYPE_MASTER_ID = 1L;
    private static final Long UPDATED_NHWY_ROAD_TYPE_MASTER_ID = 2L;

    private static final Long DEFAULT_SUB_ESTIMATE_ID = 1L;
    private static final Long UPDATED_SUB_ESTIMATE_ID = 2L;

    private static final String DEFAULT_QUARRY = "AAAAAAAAAA";
    private static final String UPDATED_QUARRY = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LEAD_IN_M = new BigDecimal(0);
    private static final BigDecimal UPDATED_LEAD_IN_M = new BigDecimal(1);

    private static final BigDecimal DEFAULT_LEAD_IN_KM = new BigDecimal(0);
    private static final BigDecimal UPDATED_LEAD_IN_KM = new BigDecimal(1);

    private static final BigDecimal DEFAULT_LEAD_CHARGES_M = new BigDecimal(1);
    private static final BigDecimal UPDATED_LEAD_CHARGES_M = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LEAD_CHARGES_KM = new BigDecimal(1);
    private static final BigDecimal UPDATED_LEAD_CHARGES_KM = new BigDecimal(2);

    private static final Boolean DEFAULT_INITIAL_LEAD_REQUIRED_YN = false;
    private static final Boolean UPDATED_INITIAL_LEAD_REQUIRED_YN = true;

    private static final String ENTITY_API_URL = "/api/work-estimate-leads";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkEstimateLeadRepository workEstimateLeadRepository;

    @Autowired
    private WorkEstimateLeadMapper workEstimateLeadMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkEstimateLeadMockMvc;

    private WorkEstimateLead workEstimateLead;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateLead createEntity(EntityManager em) {
        WorkEstimateLead workEstimateLead = new WorkEstimateLead()
            .workEstimateId(DEFAULT_WORK_ESTIMATE_ID)
            .catWorkSorItemId(DEFAULT_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(DEFAULT_MATERIAL_MASTER_ID)
            .nhwyRoadTypeMasterId(DEFAULT_NHWY_ROAD_TYPE_MASTER_ID)
            .subEstimateId(DEFAULT_SUB_ESTIMATE_ID)
            .quarry(DEFAULT_QUARRY)
            .leadInM(DEFAULT_LEAD_IN_M)
            .leadInKm(DEFAULT_LEAD_IN_KM)
            .leadChargesM(DEFAULT_LEAD_CHARGES_M)
            .leadChargesKm(DEFAULT_LEAD_CHARGES_KM)
            .initialLeadRequiredYn(DEFAULT_INITIAL_LEAD_REQUIRED_YN);
        return workEstimateLead;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateLead createUpdatedEntity(EntityManager em) {
        WorkEstimateLead workEstimateLead = new WorkEstimateLead()
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .nhwyRoadTypeMasterId(UPDATED_NHWY_ROAD_TYPE_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .quarry(UPDATED_QUARRY)
            .leadInM(UPDATED_LEAD_IN_M)
            .leadInKm(UPDATED_LEAD_IN_KM)
            .leadChargesM(UPDATED_LEAD_CHARGES_M)
            .leadChargesKm(UPDATED_LEAD_CHARGES_KM)
            .initialLeadRequiredYn(UPDATED_INITIAL_LEAD_REQUIRED_YN);
        return workEstimateLead;
    }

    @BeforeEach
    public void initTest() {
        workEstimateLead = createEntity(em);
    }

    @Test
    @Transactional
    void createWorkEstimateLead() throws Exception {
        int databaseSizeBeforeCreate = workEstimateLeadRepository.findAll().size();
        // Create the WorkEstimateLead
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);
        restWorkEstimateLeadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeCreate + 1);
        WorkEstimateLead testWorkEstimateLead = workEstimateLeadList.get(workEstimateLeadList.size() - 1);
        assertThat(testWorkEstimateLead.getWorkEstimateId()).isEqualTo(DEFAULT_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateLead.getCatWorkSorItemId()).isEqualTo(DEFAULT_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateLead.getMaterialMasterId()).isEqualTo(DEFAULT_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateLead.getNhwyRoadTypeMasterId()).isEqualTo(DEFAULT_NHWY_ROAD_TYPE_MASTER_ID);
        assertThat(testWorkEstimateLead.getSubEstimateId()).isEqualTo(DEFAULT_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateLead.getQuarry()).isEqualTo(DEFAULT_QUARRY);
        assertThat(testWorkEstimateLead.getLeadInM()).isEqualByComparingTo(DEFAULT_LEAD_IN_M);
        assertThat(testWorkEstimateLead.getLeadInKm()).isEqualByComparingTo(DEFAULT_LEAD_IN_KM);
        assertThat(testWorkEstimateLead.getLeadChargesM()).isEqualByComparingTo(DEFAULT_LEAD_CHARGES_M);
        assertThat(testWorkEstimateLead.getLeadChargesKm()).isEqualByComparingTo(DEFAULT_LEAD_CHARGES_KM);
        assertThat(testWorkEstimateLead.getInitialLeadRequiredYn()).isEqualTo(DEFAULT_INITIAL_LEAD_REQUIRED_YN);
    }

    @Test
    @Transactional
    void createWorkEstimateLeadWithExistingId() throws Exception {
        // Create the WorkEstimateLead with an existing ID
        workEstimateLead.setId(1L);
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        int databaseSizeBeforeCreate = workEstimateLeadRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkEstimateLeadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWorkEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateLeadRepository.findAll().size();
        // set the field null
        workEstimateLead.setWorkEstimateId(null);

        // Create the WorkEstimateLead, which fails.
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        restWorkEstimateLeadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCatWorkSorItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateLeadRepository.findAll().size();
        // set the field null
        workEstimateLead.setCatWorkSorItemId(null);

        // Create the WorkEstimateLead, which fails.
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        restWorkEstimateLeadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaterialMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateLeadRepository.findAll().size();
        // set the field null
        workEstimateLead.setMaterialMasterId(null);

        // Create the WorkEstimateLead, which fails.
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        restWorkEstimateLeadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateLeadRepository.findAll().size();
        // set the field null
        workEstimateLead.setSubEstimateId(null);

        // Create the WorkEstimateLead, which fails.
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        restWorkEstimateLeadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInitialLeadRequiredYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateLeadRepository.findAll().size();
        // set the field null
        workEstimateLead.setInitialLeadRequiredYn(null);

        // Create the WorkEstimateLead, which fails.
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        restWorkEstimateLeadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllWorkEstimateLeads() throws Exception {
        // Initialize the database
        workEstimateLeadRepository.saveAndFlush(workEstimateLead);

        // Get all the workEstimateLeadList
        restWorkEstimateLeadMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workEstimateLead.getId().intValue())))
            .andExpect(jsonPath("$.[*].workEstimateId").value(hasItem(DEFAULT_WORK_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].catWorkSorItemId").value(hasItem(DEFAULT_CAT_WORK_SOR_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].materialMasterId").value(hasItem(DEFAULT_MATERIAL_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].nhwyRoadTypeMasterId").value(hasItem(DEFAULT_NHWY_ROAD_TYPE_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].subEstimateId").value(hasItem(DEFAULT_SUB_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].quarry").value(hasItem(DEFAULT_QUARRY)))
            .andExpect(jsonPath("$.[*].leadInM").value(hasItem(sameNumber(DEFAULT_LEAD_IN_M))))
            .andExpect(jsonPath("$.[*].leadInKm").value(hasItem(sameNumber(DEFAULT_LEAD_IN_KM))))
            .andExpect(jsonPath("$.[*].leadChargesM").value(hasItem(sameNumber(DEFAULT_LEAD_CHARGES_M))))
            .andExpect(jsonPath("$.[*].leadChargesKm").value(hasItem(sameNumber(DEFAULT_LEAD_CHARGES_KM))))
            .andExpect(jsonPath("$.[*].initialLeadRequiredYn").value(hasItem(DEFAULT_INITIAL_LEAD_REQUIRED_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getWorkEstimateLead() throws Exception {
        // Initialize the database
        workEstimateLeadRepository.saveAndFlush(workEstimateLead);

        // Get the workEstimateLead
        restWorkEstimateLeadMockMvc
            .perform(get(ENTITY_API_URL_ID, workEstimateLead.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workEstimateLead.getId().intValue()))
            .andExpect(jsonPath("$.workEstimateId").value(DEFAULT_WORK_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.catWorkSorItemId").value(DEFAULT_CAT_WORK_SOR_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.materialMasterId").value(DEFAULT_MATERIAL_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.nhwyRoadTypeMasterId").value(DEFAULT_NHWY_ROAD_TYPE_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.subEstimateId").value(DEFAULT_SUB_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.quarry").value(DEFAULT_QUARRY))
            .andExpect(jsonPath("$.leadInM").value(sameNumber(DEFAULT_LEAD_IN_M)))
            .andExpect(jsonPath("$.leadInKm").value(sameNumber(DEFAULT_LEAD_IN_KM)))
            .andExpect(jsonPath("$.leadChargesM").value(sameNumber(DEFAULT_LEAD_CHARGES_M)))
            .andExpect(jsonPath("$.leadChargesKm").value(sameNumber(DEFAULT_LEAD_CHARGES_KM)))
            .andExpect(jsonPath("$.initialLeadRequiredYn").value(DEFAULT_INITIAL_LEAD_REQUIRED_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingWorkEstimateLead() throws Exception {
        // Get the workEstimateLead
        restWorkEstimateLeadMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWorkEstimateLead() throws Exception {
        // Initialize the database
        workEstimateLeadRepository.saveAndFlush(workEstimateLead);

        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();

        // Update the workEstimateLead
        WorkEstimateLead updatedWorkEstimateLead = workEstimateLeadRepository.findById(workEstimateLead.getId()).get();
        // Disconnect from session so that the updates on updatedWorkEstimateLead are not directly saved in db
        em.detach(updatedWorkEstimateLead);
        updatedWorkEstimateLead
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .nhwyRoadTypeMasterId(UPDATED_NHWY_ROAD_TYPE_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .quarry(UPDATED_QUARRY)
            .leadInM(UPDATED_LEAD_IN_M)
            .leadInKm(UPDATED_LEAD_IN_KM)
            .leadChargesM(UPDATED_LEAD_CHARGES_M)
            .leadChargesKm(UPDATED_LEAD_CHARGES_KM)
            .initialLeadRequiredYn(UPDATED_INITIAL_LEAD_REQUIRED_YN);
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(updatedWorkEstimateLead);

        restWorkEstimateLeadMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateLeadDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateLead testWorkEstimateLead = workEstimateLeadList.get(workEstimateLeadList.size() - 1);
        assertThat(testWorkEstimateLead.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateLead.getCatWorkSorItemId()).isEqualTo(UPDATED_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateLead.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateLead.getNhwyRoadTypeMasterId()).isEqualTo(UPDATED_NHWY_ROAD_TYPE_MASTER_ID);
        assertThat(testWorkEstimateLead.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateLead.getQuarry()).isEqualTo(UPDATED_QUARRY);
        assertThat(testWorkEstimateLead.getLeadInM()).isEqualTo(UPDATED_LEAD_IN_M);
        assertThat(testWorkEstimateLead.getLeadInKm()).isEqualTo(UPDATED_LEAD_IN_KM);
        assertThat(testWorkEstimateLead.getLeadChargesM()).isEqualTo(UPDATED_LEAD_CHARGES_M);
        assertThat(testWorkEstimateLead.getLeadChargesKm()).isEqualTo(UPDATED_LEAD_CHARGES_KM);
        assertThat(testWorkEstimateLead.getInitialLeadRequiredYn()).isEqualTo(UPDATED_INITIAL_LEAD_REQUIRED_YN);
    }

    @Test
    @Transactional
    void putNonExistingWorkEstimateLead() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();
        workEstimateLead.setId(count.incrementAndGet());

        // Create the WorkEstimateLead
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateLeadMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateLeadDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkEstimateLead() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();
        workEstimateLead.setId(count.incrementAndGet());

        // Create the WorkEstimateLead
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateLeadMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkEstimateLead() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();
        workEstimateLead.setId(count.incrementAndGet());

        // Create the WorkEstimateLead
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateLeadMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkEstimateLeadWithPatch() throws Exception {
        // Initialize the database
        workEstimateLeadRepository.saveAndFlush(workEstimateLead);

        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();

        // Update the workEstimateLead using partial update
        WorkEstimateLead partialUpdatedWorkEstimateLead = new WorkEstimateLead();
        partialUpdatedWorkEstimateLead.setId(workEstimateLead.getId());

        partialUpdatedWorkEstimateLead
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .leadInKm(UPDATED_LEAD_IN_KM)
            .leadChargesM(UPDATED_LEAD_CHARGES_M)
            .leadChargesKm(UPDATED_LEAD_CHARGES_KM);

        restWorkEstimateLeadMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateLead.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateLead))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateLead testWorkEstimateLead = workEstimateLeadList.get(workEstimateLeadList.size() - 1);
        assertThat(testWorkEstimateLead.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateLead.getCatWorkSorItemId()).isEqualTo(DEFAULT_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateLead.getMaterialMasterId()).isEqualTo(DEFAULT_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateLead.getNhwyRoadTypeMasterId()).isEqualTo(DEFAULT_NHWY_ROAD_TYPE_MASTER_ID);
        assertThat(testWorkEstimateLead.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateLead.getQuarry()).isEqualTo(DEFAULT_QUARRY);
        assertThat(testWorkEstimateLead.getLeadInM()).isEqualByComparingTo(DEFAULT_LEAD_IN_M);
        assertThat(testWorkEstimateLead.getLeadInKm()).isEqualByComparingTo(UPDATED_LEAD_IN_KM);
        assertThat(testWorkEstimateLead.getLeadChargesM()).isEqualByComparingTo(UPDATED_LEAD_CHARGES_M);
        assertThat(testWorkEstimateLead.getLeadChargesKm()).isEqualByComparingTo(UPDATED_LEAD_CHARGES_KM);
        assertThat(testWorkEstimateLead.getInitialLeadRequiredYn()).isEqualTo(DEFAULT_INITIAL_LEAD_REQUIRED_YN);
    }

    @Test
    @Transactional
    void fullUpdateWorkEstimateLeadWithPatch() throws Exception {
        // Initialize the database
        workEstimateLeadRepository.saveAndFlush(workEstimateLead);

        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();

        // Update the workEstimateLead using partial update
        WorkEstimateLead partialUpdatedWorkEstimateLead = new WorkEstimateLead();
        partialUpdatedWorkEstimateLead.setId(workEstimateLead.getId());

        partialUpdatedWorkEstimateLead
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .catWorkSorItemId(UPDATED_CAT_WORK_SOR_ITEM_ID)
            .materialMasterId(UPDATED_MATERIAL_MASTER_ID)
            .nhwyRoadTypeMasterId(UPDATED_NHWY_ROAD_TYPE_MASTER_ID)
            .subEstimateId(UPDATED_SUB_ESTIMATE_ID)
            .quarry(UPDATED_QUARRY)
            .leadInM(UPDATED_LEAD_IN_M)
            .leadInKm(UPDATED_LEAD_IN_KM)
            .leadChargesM(UPDATED_LEAD_CHARGES_M)
            .leadChargesKm(UPDATED_LEAD_CHARGES_KM)
            .initialLeadRequiredYn(UPDATED_INITIAL_LEAD_REQUIRED_YN);

        restWorkEstimateLeadMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateLead.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateLead))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateLead testWorkEstimateLead = workEstimateLeadList.get(workEstimateLeadList.size() - 1);
        assertThat(testWorkEstimateLead.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateLead.getCatWorkSorItemId()).isEqualTo(UPDATED_CAT_WORK_SOR_ITEM_ID);
        assertThat(testWorkEstimateLead.getMaterialMasterId()).isEqualTo(UPDATED_MATERIAL_MASTER_ID);
        assertThat(testWorkEstimateLead.getNhwyRoadTypeMasterId()).isEqualTo(UPDATED_NHWY_ROAD_TYPE_MASTER_ID);
        assertThat(testWorkEstimateLead.getSubEstimateId()).isEqualTo(UPDATED_SUB_ESTIMATE_ID);
        assertThat(testWorkEstimateLead.getQuarry()).isEqualTo(UPDATED_QUARRY);
        assertThat(testWorkEstimateLead.getLeadInM()).isEqualByComparingTo(UPDATED_LEAD_IN_M);
        assertThat(testWorkEstimateLead.getLeadInKm()).isEqualByComparingTo(UPDATED_LEAD_IN_KM);
        assertThat(testWorkEstimateLead.getLeadChargesM()).isEqualByComparingTo(UPDATED_LEAD_CHARGES_M);
        assertThat(testWorkEstimateLead.getLeadChargesKm()).isEqualByComparingTo(UPDATED_LEAD_CHARGES_KM);
        assertThat(testWorkEstimateLead.getInitialLeadRequiredYn()).isEqualTo(UPDATED_INITIAL_LEAD_REQUIRED_YN);
    }

    @Test
    @Transactional
    void patchNonExistingWorkEstimateLead() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();
        workEstimateLead.setId(count.incrementAndGet());

        // Create the WorkEstimateLead
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateLeadMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workEstimateLeadDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkEstimateLead() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();
        workEstimateLead.setId(count.incrementAndGet());

        // Create the WorkEstimateLead
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateLeadMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkEstimateLead() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateLeadRepository.findAll().size();
        workEstimateLead.setId(count.incrementAndGet());

        // Create the WorkEstimateLead
        WorkEstimateLeadDTO workEstimateLeadDTO = workEstimateLeadMapper.toDto(workEstimateLead);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateLeadMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateLeadDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateLead in the database
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkEstimateLead() throws Exception {
        // Initialize the database
        workEstimateLeadRepository.saveAndFlush(workEstimateLead);

        int databaseSizeBeforeDelete = workEstimateLeadRepository.findAll().size();

        // Delete the workEstimateLead
        restWorkEstimateLeadMockMvc
            .perform(delete(ENTITY_API_URL_ID, workEstimateLead.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkEstimateLead> workEstimateLeadList = workEstimateLeadRepository.findAll();
        assertThat(workEstimateLeadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
