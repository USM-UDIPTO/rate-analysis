package com.dxc.eproc.rateanalysis.web.rest;

import static com.dxc.eproc.rateanalysis.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dxc.eproc.rateanalysis.IntegrationTest;
import com.dxc.eproc.rateanalysis.domain.WorkEstimateRateAnalysis;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateRateAnalysisRepository;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRateAnalysisDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateRateAnalysisMapper;
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
 * Integration tests for the {@link WorkEstimateRateAnalysisResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkEstimateRateAnalysisResourceIT {

    private static final Long DEFAULT_WORK_ESTIMATE_ID = 1L;
    private static final Long UPDATED_WORK_ESTIMATE_ID = 2L;

    private static final Long DEFAULT_AREA_WEIGHTAGE_MASTER_ID = 1L;
    private static final Long UPDATED_AREA_WEIGHTAGE_MASTER_ID = 2L;

    private static final Long DEFAULT_AREA_WEIGHTAGE_CIRCLE_ID = 1L;
    private static final Long UPDATED_AREA_WEIGHTAGE_CIRCLE_ID = 2L;

    private static final BigDecimal DEFAULT_AREA_WEIGHTAGE_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_AREA_WEIGHTAGE_PERCENTAGE = new BigDecimal(2);

    private static final String DEFAULT_SOR_FINANCIAL_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_SOR_FINANCIAL_YEAR = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BASIC_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BASIC_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NET_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_NET_RATE = new BigDecimal(2);

    private static final Long DEFAULT_FLOOR_NO = 1L;
    private static final Long UPDATED_FLOOR_NO = 2L;

    private static final BigDecimal DEFAULT_CONTRACTOR_PROFIT_PERCENTAGE = new BigDecimal(0);
    private static final BigDecimal UPDATED_CONTRACTOR_PROFIT_PERCENTAGE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_OVERHEAD_PERCENTAGE = new BigDecimal(0);
    private static final BigDecimal UPDATED_OVERHEAD_PERCENTAGE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TAX_PERCENTAGE = new BigDecimal(0);
    private static final BigDecimal UPDATED_TAX_PERCENTAGE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_LIFT_CHARGES = new BigDecimal(1);
    private static final BigDecimal UPDATED_LIFT_CHARGES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LOCALITY_ALLOWANCE = new BigDecimal(0);
    private static final BigDecimal UPDATED_LOCALITY_ALLOWANCE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_EMPLOYEES_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_EMPLOYEES_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONTINGENCIES = new BigDecimal(0);
    private static final BigDecimal UPDATED_CONTINGENCIES = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TRANSPORTATION_COST = new BigDecimal(0);
    private static final BigDecimal UPDATED_TRANSPORTATION_COST = new BigDecimal(1);

    private static final BigDecimal DEFAULT_SERVICE_TAX = new BigDecimal(0);
    private static final BigDecimal UPDATED_SERVICE_TAX = new BigDecimal(1);

    private static final BigDecimal DEFAULT_PROVIDENT_FUND_CHARGES = new BigDecimal(0);
    private static final BigDecimal UPDATED_PROVIDENT_FUND_CHARGES = new BigDecimal(1);

    private static final BigDecimal DEFAULT_ESI_CHARGES = new BigDecimal(0);
    private static final BigDecimal UPDATED_ESI_CHARGES = new BigDecimal(1);

    private static final BigDecimal DEFAULT_IDC_CHARGES = new BigDecimal(0);
    private static final BigDecimal UPDATED_IDC_CHARGES = new BigDecimal(1);

    private static final BigDecimal DEFAULT_WATCH_AND_WARD_COST = new BigDecimal(0);
    private static final BigDecimal UPDATED_WATCH_AND_WARD_COST = new BigDecimal(1);

    private static final BigDecimal DEFAULT_INSURANCE_COST = new BigDecimal(0);
    private static final BigDecimal UPDATED_INSURANCE_COST = new BigDecimal(1);

    private static final BigDecimal DEFAULT_STATUTORY_CHARGES = new BigDecimal(1);
    private static final BigDecimal UPDATED_STATUTORY_CHARGES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_COMPENSATION_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_COMPENSATION_COST = new BigDecimal(2);

    private static final Boolean DEFAULT_RA_COMPLETED_YN = false;
    private static final Boolean UPDATED_RA_COMPLETED_YN = true;

    private static final String ENTITY_API_URL = "/api/work-estimate-rate-analyses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkEstimateRateAnalysisRepository workEstimateRateAnalysisRepository;

    @Autowired
    private WorkEstimateRateAnalysisMapper workEstimateRateAnalysisMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkEstimateRateAnalysisMockMvc;

    private WorkEstimateRateAnalysis workEstimateRateAnalysis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateRateAnalysis createEntity(EntityManager em) {
        WorkEstimateRateAnalysis workEstimateRateAnalysis = new WorkEstimateRateAnalysis()
            .workEstimateId(DEFAULT_WORK_ESTIMATE_ID)
            .areaWeightageMasterId(DEFAULT_AREA_WEIGHTAGE_MASTER_ID)
            .areaWeightageCircleId(DEFAULT_AREA_WEIGHTAGE_CIRCLE_ID)
            .areaWeightagePercentage(DEFAULT_AREA_WEIGHTAGE_PERCENTAGE)
            .sorFinancialYear(DEFAULT_SOR_FINANCIAL_YEAR)
            .basicRate(DEFAULT_BASIC_RATE)
            .netRate(DEFAULT_NET_RATE)
            .floorNo(DEFAULT_FLOOR_NO)
            .contractorProfitPercentage(DEFAULT_CONTRACTOR_PROFIT_PERCENTAGE)
            .overheadPercentage(DEFAULT_OVERHEAD_PERCENTAGE)
            .taxPercentage(DEFAULT_TAX_PERCENTAGE)
            .liftCharges(DEFAULT_LIFT_CHARGES)
            .localityAllowance(DEFAULT_LOCALITY_ALLOWANCE)
            .employeesCost(DEFAULT_EMPLOYEES_COST)
            .contingencies(DEFAULT_CONTINGENCIES)
            .transportationCost(DEFAULT_TRANSPORTATION_COST)
            .serviceTax(DEFAULT_SERVICE_TAX)
            .providentFundCharges(DEFAULT_PROVIDENT_FUND_CHARGES)
            .esiCharges(DEFAULT_ESI_CHARGES)
            .idcCharges(DEFAULT_IDC_CHARGES)
            .watchAndWardCost(DEFAULT_WATCH_AND_WARD_COST)
            .insuranceCost(DEFAULT_INSURANCE_COST)
            .statutoryCharges(DEFAULT_STATUTORY_CHARGES)
            .compensationCost(DEFAULT_COMPENSATION_COST)
            .raCompletedYn(DEFAULT_RA_COMPLETED_YN);
        return workEstimateRateAnalysis;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkEstimateRateAnalysis createUpdatedEntity(EntityManager em) {
        WorkEstimateRateAnalysis workEstimateRateAnalysis = new WorkEstimateRateAnalysis()
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .areaWeightageMasterId(UPDATED_AREA_WEIGHTAGE_MASTER_ID)
            .areaWeightageCircleId(UPDATED_AREA_WEIGHTAGE_CIRCLE_ID)
            .areaWeightagePercentage(UPDATED_AREA_WEIGHTAGE_PERCENTAGE)
            .sorFinancialYear(UPDATED_SOR_FINANCIAL_YEAR)
            .basicRate(UPDATED_BASIC_RATE)
            .netRate(UPDATED_NET_RATE)
            .floorNo(UPDATED_FLOOR_NO)
            .contractorProfitPercentage(UPDATED_CONTRACTOR_PROFIT_PERCENTAGE)
            .overheadPercentage(UPDATED_OVERHEAD_PERCENTAGE)
            .taxPercentage(UPDATED_TAX_PERCENTAGE)
            .liftCharges(UPDATED_LIFT_CHARGES)
            .localityAllowance(UPDATED_LOCALITY_ALLOWANCE)
            .employeesCost(UPDATED_EMPLOYEES_COST)
            .contingencies(UPDATED_CONTINGENCIES)
            .transportationCost(UPDATED_TRANSPORTATION_COST)
            .serviceTax(UPDATED_SERVICE_TAX)
            .providentFundCharges(UPDATED_PROVIDENT_FUND_CHARGES)
            .esiCharges(UPDATED_ESI_CHARGES)
            .idcCharges(UPDATED_IDC_CHARGES)
            .watchAndWardCost(UPDATED_WATCH_AND_WARD_COST)
            .insuranceCost(UPDATED_INSURANCE_COST)
            .statutoryCharges(UPDATED_STATUTORY_CHARGES)
            .compensationCost(UPDATED_COMPENSATION_COST)
            .raCompletedYn(UPDATED_RA_COMPLETED_YN);
        return workEstimateRateAnalysis;
    }

    @BeforeEach
    public void initTest() {
        workEstimateRateAnalysis = createEntity(em);
    }

    @Test
    @Transactional
    void createWorkEstimateRateAnalysis() throws Exception {
        int databaseSizeBeforeCreate = workEstimateRateAnalysisRepository.findAll().size();
        // Create the WorkEstimateRateAnalysis
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);
        restWorkEstimateRateAnalysisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeCreate + 1);
        WorkEstimateRateAnalysis testWorkEstimateRateAnalysis = workEstimateRateAnalysisList.get(workEstimateRateAnalysisList.size() - 1);
        assertThat(testWorkEstimateRateAnalysis.getWorkEstimateId()).isEqualTo(DEFAULT_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightageMasterId()).isEqualTo(DEFAULT_AREA_WEIGHTAGE_MASTER_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightageCircleId()).isEqualTo(DEFAULT_AREA_WEIGHTAGE_CIRCLE_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightagePercentage()).isEqualByComparingTo(DEFAULT_AREA_WEIGHTAGE_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getSorFinancialYear()).isEqualTo(DEFAULT_SOR_FINANCIAL_YEAR);
        assertThat(testWorkEstimateRateAnalysis.getBasicRate()).isEqualByComparingTo(DEFAULT_BASIC_RATE);
        assertThat(testWorkEstimateRateAnalysis.getNetRate()).isEqualByComparingTo(DEFAULT_NET_RATE);
        assertThat(testWorkEstimateRateAnalysis.getFloorNo()).isEqualTo(DEFAULT_FLOOR_NO);
        assertThat(testWorkEstimateRateAnalysis.getContractorProfitPercentage()).isEqualByComparingTo(DEFAULT_CONTRACTOR_PROFIT_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getOverheadPercentage()).isEqualByComparingTo(DEFAULT_OVERHEAD_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getTaxPercentage()).isEqualByComparingTo(DEFAULT_TAX_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getLiftCharges()).isEqualByComparingTo(DEFAULT_LIFT_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getLocalityAllowance()).isEqualByComparingTo(DEFAULT_LOCALITY_ALLOWANCE);
        assertThat(testWorkEstimateRateAnalysis.getEmployeesCost()).isEqualByComparingTo(DEFAULT_EMPLOYEES_COST);
        assertThat(testWorkEstimateRateAnalysis.getContingencies()).isEqualByComparingTo(DEFAULT_CONTINGENCIES);
        assertThat(testWorkEstimateRateAnalysis.getTransportationCost()).isEqualByComparingTo(DEFAULT_TRANSPORTATION_COST);
        assertThat(testWorkEstimateRateAnalysis.getServiceTax()).isEqualByComparingTo(DEFAULT_SERVICE_TAX);
        assertThat(testWorkEstimateRateAnalysis.getProvidentFundCharges()).isEqualByComparingTo(DEFAULT_PROVIDENT_FUND_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getEsiCharges()).isEqualByComparingTo(DEFAULT_ESI_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getIdcCharges()).isEqualByComparingTo(DEFAULT_IDC_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getWatchAndWardCost()).isEqualByComparingTo(DEFAULT_WATCH_AND_WARD_COST);
        assertThat(testWorkEstimateRateAnalysis.getInsuranceCost()).isEqualByComparingTo(DEFAULT_INSURANCE_COST);
        assertThat(testWorkEstimateRateAnalysis.getStatutoryCharges()).isEqualByComparingTo(DEFAULT_STATUTORY_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getCompensationCost()).isEqualByComparingTo(DEFAULT_COMPENSATION_COST);
        assertThat(testWorkEstimateRateAnalysis.getRaCompletedYn()).isEqualTo(DEFAULT_RA_COMPLETED_YN);
    }

    @Test
    @Transactional
    void createWorkEstimateRateAnalysisWithExistingId() throws Exception {
        // Create the WorkEstimateRateAnalysis with an existing ID
        workEstimateRateAnalysis.setId(1L);
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        int databaseSizeBeforeCreate = workEstimateRateAnalysisRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkEstimateRateAnalysisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWorkEstimateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRateAnalysisRepository.findAll().size();
        // set the field null
        workEstimateRateAnalysis.setWorkEstimateId(null);

        // Create the WorkEstimateRateAnalysis, which fails.
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        restWorkEstimateRateAnalysisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBasicRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRateAnalysisRepository.findAll().size();
        // set the field null
        workEstimateRateAnalysis.setBasicRate(null);

        // Create the WorkEstimateRateAnalysis, which fails.
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        restWorkEstimateRateAnalysisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNetRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRateAnalysisRepository.findAll().size();
        // set the field null
        workEstimateRateAnalysis.setNetRate(null);

        // Create the WorkEstimateRateAnalysis, which fails.
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        restWorkEstimateRateAnalysisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRaCompletedYnIsRequired() throws Exception {
        int databaseSizeBeforeTest = workEstimateRateAnalysisRepository.findAll().size();
        // set the field null
        workEstimateRateAnalysis.setRaCompletedYn(null);

        // Create the WorkEstimateRateAnalysis, which fails.
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        restWorkEstimateRateAnalysisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllWorkEstimateRateAnalyses() throws Exception {
        // Initialize the database
        workEstimateRateAnalysisRepository.saveAndFlush(workEstimateRateAnalysis);

        // Get all the workEstimateRateAnalysisList
        restWorkEstimateRateAnalysisMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workEstimateRateAnalysis.getId().intValue())))
            .andExpect(jsonPath("$.[*].workEstimateId").value(hasItem(DEFAULT_WORK_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].areaWeightageMasterId").value(hasItem(DEFAULT_AREA_WEIGHTAGE_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].areaWeightageCircleId").value(hasItem(DEFAULT_AREA_WEIGHTAGE_CIRCLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].areaWeightagePercentage").value(hasItem(sameNumber(DEFAULT_AREA_WEIGHTAGE_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].sorFinancialYear").value(hasItem(DEFAULT_SOR_FINANCIAL_YEAR)))
            .andExpect(jsonPath("$.[*].basicRate").value(hasItem(sameNumber(DEFAULT_BASIC_RATE))))
            .andExpect(jsonPath("$.[*].netRate").value(hasItem(sameNumber(DEFAULT_NET_RATE))))
            .andExpect(jsonPath("$.[*].floorNo").value(hasItem(DEFAULT_FLOOR_NO.intValue())))
            .andExpect(jsonPath("$.[*].contractorProfitPercentage").value(hasItem(sameNumber(DEFAULT_CONTRACTOR_PROFIT_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].overheadPercentage").value(hasItem(sameNumber(DEFAULT_OVERHEAD_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].taxPercentage").value(hasItem(sameNumber(DEFAULT_TAX_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].liftCharges").value(hasItem(sameNumber(DEFAULT_LIFT_CHARGES))))
            .andExpect(jsonPath("$.[*].localityAllowance").value(hasItem(sameNumber(DEFAULT_LOCALITY_ALLOWANCE))))
            .andExpect(jsonPath("$.[*].employeesCost").value(hasItem(sameNumber(DEFAULT_EMPLOYEES_COST))))
            .andExpect(jsonPath("$.[*].contingencies").value(hasItem(sameNumber(DEFAULT_CONTINGENCIES))))
            .andExpect(jsonPath("$.[*].transportationCost").value(hasItem(sameNumber(DEFAULT_TRANSPORTATION_COST))))
            .andExpect(jsonPath("$.[*].serviceTax").value(hasItem(sameNumber(DEFAULT_SERVICE_TAX))))
            .andExpect(jsonPath("$.[*].providentFundCharges").value(hasItem(sameNumber(DEFAULT_PROVIDENT_FUND_CHARGES))))
            .andExpect(jsonPath("$.[*].esiCharges").value(hasItem(sameNumber(DEFAULT_ESI_CHARGES))))
            .andExpect(jsonPath("$.[*].idcCharges").value(hasItem(sameNumber(DEFAULT_IDC_CHARGES))))
            .andExpect(jsonPath("$.[*].watchAndWardCost").value(hasItem(sameNumber(DEFAULT_WATCH_AND_WARD_COST))))
            .andExpect(jsonPath("$.[*].insuranceCost").value(hasItem(sameNumber(DEFAULT_INSURANCE_COST))))
            .andExpect(jsonPath("$.[*].statutoryCharges").value(hasItem(sameNumber(DEFAULT_STATUTORY_CHARGES))))
            .andExpect(jsonPath("$.[*].compensationCost").value(hasItem(sameNumber(DEFAULT_COMPENSATION_COST))))
            .andExpect(jsonPath("$.[*].raCompletedYn").value(hasItem(DEFAULT_RA_COMPLETED_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getWorkEstimateRateAnalysis() throws Exception {
        // Initialize the database
        workEstimateRateAnalysisRepository.saveAndFlush(workEstimateRateAnalysis);

        // Get the workEstimateRateAnalysis
        restWorkEstimateRateAnalysisMockMvc
            .perform(get(ENTITY_API_URL_ID, workEstimateRateAnalysis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workEstimateRateAnalysis.getId().intValue()))
            .andExpect(jsonPath("$.workEstimateId").value(DEFAULT_WORK_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.areaWeightageMasterId").value(DEFAULT_AREA_WEIGHTAGE_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.areaWeightageCircleId").value(DEFAULT_AREA_WEIGHTAGE_CIRCLE_ID.intValue()))
            .andExpect(jsonPath("$.areaWeightagePercentage").value(sameNumber(DEFAULT_AREA_WEIGHTAGE_PERCENTAGE)))
            .andExpect(jsonPath("$.sorFinancialYear").value(DEFAULT_SOR_FINANCIAL_YEAR))
            .andExpect(jsonPath("$.basicRate").value(sameNumber(DEFAULT_BASIC_RATE)))
            .andExpect(jsonPath("$.netRate").value(sameNumber(DEFAULT_NET_RATE)))
            .andExpect(jsonPath("$.floorNo").value(DEFAULT_FLOOR_NO.intValue()))
            .andExpect(jsonPath("$.contractorProfitPercentage").value(sameNumber(DEFAULT_CONTRACTOR_PROFIT_PERCENTAGE)))
            .andExpect(jsonPath("$.overheadPercentage").value(sameNumber(DEFAULT_OVERHEAD_PERCENTAGE)))
            .andExpect(jsonPath("$.taxPercentage").value(sameNumber(DEFAULT_TAX_PERCENTAGE)))
            .andExpect(jsonPath("$.liftCharges").value(sameNumber(DEFAULT_LIFT_CHARGES)))
            .andExpect(jsonPath("$.localityAllowance").value(sameNumber(DEFAULT_LOCALITY_ALLOWANCE)))
            .andExpect(jsonPath("$.employeesCost").value(sameNumber(DEFAULT_EMPLOYEES_COST)))
            .andExpect(jsonPath("$.contingencies").value(sameNumber(DEFAULT_CONTINGENCIES)))
            .andExpect(jsonPath("$.transportationCost").value(sameNumber(DEFAULT_TRANSPORTATION_COST)))
            .andExpect(jsonPath("$.serviceTax").value(sameNumber(DEFAULT_SERVICE_TAX)))
            .andExpect(jsonPath("$.providentFundCharges").value(sameNumber(DEFAULT_PROVIDENT_FUND_CHARGES)))
            .andExpect(jsonPath("$.esiCharges").value(sameNumber(DEFAULT_ESI_CHARGES)))
            .andExpect(jsonPath("$.idcCharges").value(sameNumber(DEFAULT_IDC_CHARGES)))
            .andExpect(jsonPath("$.watchAndWardCost").value(sameNumber(DEFAULT_WATCH_AND_WARD_COST)))
            .andExpect(jsonPath("$.insuranceCost").value(sameNumber(DEFAULT_INSURANCE_COST)))
            .andExpect(jsonPath("$.statutoryCharges").value(sameNumber(DEFAULT_STATUTORY_CHARGES)))
            .andExpect(jsonPath("$.compensationCost").value(sameNumber(DEFAULT_COMPENSATION_COST)))
            .andExpect(jsonPath("$.raCompletedYn").value(DEFAULT_RA_COMPLETED_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingWorkEstimateRateAnalysis() throws Exception {
        // Get the workEstimateRateAnalysis
        restWorkEstimateRateAnalysisMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWorkEstimateRateAnalysis() throws Exception {
        // Initialize the database
        workEstimateRateAnalysisRepository.saveAndFlush(workEstimateRateAnalysis);

        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();

        // Update the workEstimateRateAnalysis
        WorkEstimateRateAnalysis updatedWorkEstimateRateAnalysis = workEstimateRateAnalysisRepository
            .findById(workEstimateRateAnalysis.getId())
            .get();
        // Disconnect from session so that the updates on updatedWorkEstimateRateAnalysis are not directly saved in db
        em.detach(updatedWorkEstimateRateAnalysis);
        updatedWorkEstimateRateAnalysis
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .areaWeightageMasterId(UPDATED_AREA_WEIGHTAGE_MASTER_ID)
            .areaWeightageCircleId(UPDATED_AREA_WEIGHTAGE_CIRCLE_ID)
            .areaWeightagePercentage(UPDATED_AREA_WEIGHTAGE_PERCENTAGE)
            .sorFinancialYear(UPDATED_SOR_FINANCIAL_YEAR)
            .basicRate(UPDATED_BASIC_RATE)
            .netRate(UPDATED_NET_RATE)
            .floorNo(UPDATED_FLOOR_NO)
            .contractorProfitPercentage(UPDATED_CONTRACTOR_PROFIT_PERCENTAGE)
            .overheadPercentage(UPDATED_OVERHEAD_PERCENTAGE)
            .taxPercentage(UPDATED_TAX_PERCENTAGE)
            .liftCharges(UPDATED_LIFT_CHARGES)
            .localityAllowance(UPDATED_LOCALITY_ALLOWANCE)
            .employeesCost(UPDATED_EMPLOYEES_COST)
            .contingencies(UPDATED_CONTINGENCIES)
            .transportationCost(UPDATED_TRANSPORTATION_COST)
            .serviceTax(UPDATED_SERVICE_TAX)
            .providentFundCharges(UPDATED_PROVIDENT_FUND_CHARGES)
            .esiCharges(UPDATED_ESI_CHARGES)
            .idcCharges(UPDATED_IDC_CHARGES)
            .watchAndWardCost(UPDATED_WATCH_AND_WARD_COST)
            .insuranceCost(UPDATED_INSURANCE_COST)
            .statutoryCharges(UPDATED_STATUTORY_CHARGES)
            .compensationCost(UPDATED_COMPENSATION_COST)
            .raCompletedYn(UPDATED_RA_COMPLETED_YN);
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(updatedWorkEstimateRateAnalysis);

        restWorkEstimateRateAnalysisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateRateAnalysisDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRateAnalysis testWorkEstimateRateAnalysis = workEstimateRateAnalysisList.get(workEstimateRateAnalysisList.size() - 1);
        assertThat(testWorkEstimateRateAnalysis.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightageMasterId()).isEqualTo(UPDATED_AREA_WEIGHTAGE_MASTER_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightageCircleId()).isEqualTo(UPDATED_AREA_WEIGHTAGE_CIRCLE_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightagePercentage()).isEqualTo(UPDATED_AREA_WEIGHTAGE_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getSorFinancialYear()).isEqualTo(UPDATED_SOR_FINANCIAL_YEAR);
        assertThat(testWorkEstimateRateAnalysis.getBasicRate()).isEqualTo(UPDATED_BASIC_RATE);
        assertThat(testWorkEstimateRateAnalysis.getNetRate()).isEqualTo(UPDATED_NET_RATE);
        assertThat(testWorkEstimateRateAnalysis.getFloorNo()).isEqualTo(UPDATED_FLOOR_NO);
        assertThat(testWorkEstimateRateAnalysis.getContractorProfitPercentage()).isEqualTo(UPDATED_CONTRACTOR_PROFIT_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getOverheadPercentage()).isEqualTo(UPDATED_OVERHEAD_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getTaxPercentage()).isEqualTo(UPDATED_TAX_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getLiftCharges()).isEqualTo(UPDATED_LIFT_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getLocalityAllowance()).isEqualTo(UPDATED_LOCALITY_ALLOWANCE);
        assertThat(testWorkEstimateRateAnalysis.getEmployeesCost()).isEqualTo(UPDATED_EMPLOYEES_COST);
        assertThat(testWorkEstimateRateAnalysis.getContingencies()).isEqualTo(UPDATED_CONTINGENCIES);
        assertThat(testWorkEstimateRateAnalysis.getTransportationCost()).isEqualTo(UPDATED_TRANSPORTATION_COST);
        assertThat(testWorkEstimateRateAnalysis.getServiceTax()).isEqualTo(UPDATED_SERVICE_TAX);
        assertThat(testWorkEstimateRateAnalysis.getProvidentFundCharges()).isEqualTo(UPDATED_PROVIDENT_FUND_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getEsiCharges()).isEqualTo(UPDATED_ESI_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getIdcCharges()).isEqualTo(UPDATED_IDC_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getWatchAndWardCost()).isEqualTo(UPDATED_WATCH_AND_WARD_COST);
        assertThat(testWorkEstimateRateAnalysis.getInsuranceCost()).isEqualTo(UPDATED_INSURANCE_COST);
        assertThat(testWorkEstimateRateAnalysis.getStatutoryCharges()).isEqualTo(UPDATED_STATUTORY_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getCompensationCost()).isEqualTo(UPDATED_COMPENSATION_COST);
        assertThat(testWorkEstimateRateAnalysis.getRaCompletedYn()).isEqualTo(UPDATED_RA_COMPLETED_YN);
    }

    @Test
    @Transactional
    void putNonExistingWorkEstimateRateAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();
        workEstimateRateAnalysis.setId(count.incrementAndGet());

        // Create the WorkEstimateRateAnalysis
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateRateAnalysisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workEstimateRateAnalysisDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkEstimateRateAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();
        workEstimateRateAnalysis.setId(count.incrementAndGet());

        // Create the WorkEstimateRateAnalysis
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRateAnalysisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkEstimateRateAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();
        workEstimateRateAnalysis.setId(count.incrementAndGet());

        // Create the WorkEstimateRateAnalysis
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRateAnalysisMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkEstimateRateAnalysisWithPatch() throws Exception {
        // Initialize the database
        workEstimateRateAnalysisRepository.saveAndFlush(workEstimateRateAnalysis);

        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();

        // Update the workEstimateRateAnalysis using partial update
        WorkEstimateRateAnalysis partialUpdatedWorkEstimateRateAnalysis = new WorkEstimateRateAnalysis();
        partialUpdatedWorkEstimateRateAnalysis.setId(workEstimateRateAnalysis.getId());

        partialUpdatedWorkEstimateRateAnalysis
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .areaWeightagePercentage(UPDATED_AREA_WEIGHTAGE_PERCENTAGE)
            .basicRate(UPDATED_BASIC_RATE)
            .contractorProfitPercentage(UPDATED_CONTRACTOR_PROFIT_PERCENTAGE)
            .taxPercentage(UPDATED_TAX_PERCENTAGE)
            .liftCharges(UPDATED_LIFT_CHARGES)
            .localityAllowance(UPDATED_LOCALITY_ALLOWANCE)
            .contingencies(UPDATED_CONTINGENCIES)
            .transportationCost(UPDATED_TRANSPORTATION_COST)
            .serviceTax(UPDATED_SERVICE_TAX)
            .esiCharges(UPDATED_ESI_CHARGES)
            .insuranceCost(UPDATED_INSURANCE_COST)
            .statutoryCharges(UPDATED_STATUTORY_CHARGES);

        restWorkEstimateRateAnalysisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateRateAnalysis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateRateAnalysis))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRateAnalysis testWorkEstimateRateAnalysis = workEstimateRateAnalysisList.get(workEstimateRateAnalysisList.size() - 1);
        assertThat(testWorkEstimateRateAnalysis.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightageMasterId()).isEqualTo(DEFAULT_AREA_WEIGHTAGE_MASTER_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightageCircleId()).isEqualTo(DEFAULT_AREA_WEIGHTAGE_CIRCLE_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightagePercentage()).isEqualByComparingTo(UPDATED_AREA_WEIGHTAGE_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getSorFinancialYear()).isEqualTo(DEFAULT_SOR_FINANCIAL_YEAR);
        assertThat(testWorkEstimateRateAnalysis.getBasicRate()).isEqualByComparingTo(UPDATED_BASIC_RATE);
        assertThat(testWorkEstimateRateAnalysis.getNetRate()).isEqualByComparingTo(DEFAULT_NET_RATE);
        assertThat(testWorkEstimateRateAnalysis.getFloorNo()).isEqualTo(DEFAULT_FLOOR_NO);
        assertThat(testWorkEstimateRateAnalysis.getContractorProfitPercentage()).isEqualByComparingTo(UPDATED_CONTRACTOR_PROFIT_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getOverheadPercentage()).isEqualByComparingTo(DEFAULT_OVERHEAD_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getTaxPercentage()).isEqualByComparingTo(UPDATED_TAX_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getLiftCharges()).isEqualByComparingTo(UPDATED_LIFT_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getLocalityAllowance()).isEqualByComparingTo(UPDATED_LOCALITY_ALLOWANCE);
        assertThat(testWorkEstimateRateAnalysis.getEmployeesCost()).isEqualByComparingTo(DEFAULT_EMPLOYEES_COST);
        assertThat(testWorkEstimateRateAnalysis.getContingencies()).isEqualByComparingTo(UPDATED_CONTINGENCIES);
        assertThat(testWorkEstimateRateAnalysis.getTransportationCost()).isEqualByComparingTo(UPDATED_TRANSPORTATION_COST);
        assertThat(testWorkEstimateRateAnalysis.getServiceTax()).isEqualByComparingTo(UPDATED_SERVICE_TAX);
        assertThat(testWorkEstimateRateAnalysis.getProvidentFundCharges()).isEqualByComparingTo(DEFAULT_PROVIDENT_FUND_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getEsiCharges()).isEqualByComparingTo(UPDATED_ESI_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getIdcCharges()).isEqualByComparingTo(DEFAULT_IDC_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getWatchAndWardCost()).isEqualByComparingTo(DEFAULT_WATCH_AND_WARD_COST);
        assertThat(testWorkEstimateRateAnalysis.getInsuranceCost()).isEqualByComparingTo(UPDATED_INSURANCE_COST);
        assertThat(testWorkEstimateRateAnalysis.getStatutoryCharges()).isEqualByComparingTo(UPDATED_STATUTORY_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getCompensationCost()).isEqualByComparingTo(DEFAULT_COMPENSATION_COST);
        assertThat(testWorkEstimateRateAnalysis.getRaCompletedYn()).isEqualTo(DEFAULT_RA_COMPLETED_YN);
    }

    @Test
    @Transactional
    void fullUpdateWorkEstimateRateAnalysisWithPatch() throws Exception {
        // Initialize the database
        workEstimateRateAnalysisRepository.saveAndFlush(workEstimateRateAnalysis);

        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();

        // Update the workEstimateRateAnalysis using partial update
        WorkEstimateRateAnalysis partialUpdatedWorkEstimateRateAnalysis = new WorkEstimateRateAnalysis();
        partialUpdatedWorkEstimateRateAnalysis.setId(workEstimateRateAnalysis.getId());

        partialUpdatedWorkEstimateRateAnalysis
            .workEstimateId(UPDATED_WORK_ESTIMATE_ID)
            .areaWeightageMasterId(UPDATED_AREA_WEIGHTAGE_MASTER_ID)
            .areaWeightageCircleId(UPDATED_AREA_WEIGHTAGE_CIRCLE_ID)
            .areaWeightagePercentage(UPDATED_AREA_WEIGHTAGE_PERCENTAGE)
            .sorFinancialYear(UPDATED_SOR_FINANCIAL_YEAR)
            .basicRate(UPDATED_BASIC_RATE)
            .netRate(UPDATED_NET_RATE)
            .floorNo(UPDATED_FLOOR_NO)
            .contractorProfitPercentage(UPDATED_CONTRACTOR_PROFIT_PERCENTAGE)
            .overheadPercentage(UPDATED_OVERHEAD_PERCENTAGE)
            .taxPercentage(UPDATED_TAX_PERCENTAGE)
            .liftCharges(UPDATED_LIFT_CHARGES)
            .localityAllowance(UPDATED_LOCALITY_ALLOWANCE)
            .employeesCost(UPDATED_EMPLOYEES_COST)
            .contingencies(UPDATED_CONTINGENCIES)
            .transportationCost(UPDATED_TRANSPORTATION_COST)
            .serviceTax(UPDATED_SERVICE_TAX)
            .providentFundCharges(UPDATED_PROVIDENT_FUND_CHARGES)
            .esiCharges(UPDATED_ESI_CHARGES)
            .idcCharges(UPDATED_IDC_CHARGES)
            .watchAndWardCost(UPDATED_WATCH_AND_WARD_COST)
            .insuranceCost(UPDATED_INSURANCE_COST)
            .statutoryCharges(UPDATED_STATUTORY_CHARGES)
            .compensationCost(UPDATED_COMPENSATION_COST)
            .raCompletedYn(UPDATED_RA_COMPLETED_YN);

        restWorkEstimateRateAnalysisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkEstimateRateAnalysis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkEstimateRateAnalysis))
            )
            .andExpect(status().isOk());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
        WorkEstimateRateAnalysis testWorkEstimateRateAnalysis = workEstimateRateAnalysisList.get(workEstimateRateAnalysisList.size() - 1);
        assertThat(testWorkEstimateRateAnalysis.getWorkEstimateId()).isEqualTo(UPDATED_WORK_ESTIMATE_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightageMasterId()).isEqualTo(UPDATED_AREA_WEIGHTAGE_MASTER_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightageCircleId()).isEqualTo(UPDATED_AREA_WEIGHTAGE_CIRCLE_ID);
        assertThat(testWorkEstimateRateAnalysis.getAreaWeightagePercentage()).isEqualByComparingTo(UPDATED_AREA_WEIGHTAGE_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getSorFinancialYear()).isEqualTo(UPDATED_SOR_FINANCIAL_YEAR);
        assertThat(testWorkEstimateRateAnalysis.getBasicRate()).isEqualByComparingTo(UPDATED_BASIC_RATE);
        assertThat(testWorkEstimateRateAnalysis.getNetRate()).isEqualByComparingTo(UPDATED_NET_RATE);
        assertThat(testWorkEstimateRateAnalysis.getFloorNo()).isEqualTo(UPDATED_FLOOR_NO);
        assertThat(testWorkEstimateRateAnalysis.getContractorProfitPercentage()).isEqualByComparingTo(UPDATED_CONTRACTOR_PROFIT_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getOverheadPercentage()).isEqualByComparingTo(UPDATED_OVERHEAD_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getTaxPercentage()).isEqualByComparingTo(UPDATED_TAX_PERCENTAGE);
        assertThat(testWorkEstimateRateAnalysis.getLiftCharges()).isEqualByComparingTo(UPDATED_LIFT_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getLocalityAllowance()).isEqualByComparingTo(UPDATED_LOCALITY_ALLOWANCE);
        assertThat(testWorkEstimateRateAnalysis.getEmployeesCost()).isEqualByComparingTo(UPDATED_EMPLOYEES_COST);
        assertThat(testWorkEstimateRateAnalysis.getContingencies()).isEqualByComparingTo(UPDATED_CONTINGENCIES);
        assertThat(testWorkEstimateRateAnalysis.getTransportationCost()).isEqualByComparingTo(UPDATED_TRANSPORTATION_COST);
        assertThat(testWorkEstimateRateAnalysis.getServiceTax()).isEqualByComparingTo(UPDATED_SERVICE_TAX);
        assertThat(testWorkEstimateRateAnalysis.getProvidentFundCharges()).isEqualByComparingTo(UPDATED_PROVIDENT_FUND_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getEsiCharges()).isEqualByComparingTo(UPDATED_ESI_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getIdcCharges()).isEqualByComparingTo(UPDATED_IDC_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getWatchAndWardCost()).isEqualByComparingTo(UPDATED_WATCH_AND_WARD_COST);
        assertThat(testWorkEstimateRateAnalysis.getInsuranceCost()).isEqualByComparingTo(UPDATED_INSURANCE_COST);
        assertThat(testWorkEstimateRateAnalysis.getStatutoryCharges()).isEqualByComparingTo(UPDATED_STATUTORY_CHARGES);
        assertThat(testWorkEstimateRateAnalysis.getCompensationCost()).isEqualByComparingTo(UPDATED_COMPENSATION_COST);
        assertThat(testWorkEstimateRateAnalysis.getRaCompletedYn()).isEqualTo(UPDATED_RA_COMPLETED_YN);
    }

    @Test
    @Transactional
    void patchNonExistingWorkEstimateRateAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();
        workEstimateRateAnalysis.setId(count.incrementAndGet());

        // Create the WorkEstimateRateAnalysis
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkEstimateRateAnalysisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workEstimateRateAnalysisDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkEstimateRateAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();
        workEstimateRateAnalysis.setId(count.incrementAndGet());

        // Create the WorkEstimateRateAnalysis
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRateAnalysisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkEstimateRateAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = workEstimateRateAnalysisRepository.findAll().size();
        workEstimateRateAnalysis.setId(count.incrementAndGet());

        // Create the WorkEstimateRateAnalysis
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkEstimateRateAnalysisMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(workEstimateRateAnalysisDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WorkEstimateRateAnalysis in the database
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkEstimateRateAnalysis() throws Exception {
        // Initialize the database
        workEstimateRateAnalysisRepository.saveAndFlush(workEstimateRateAnalysis);

        int databaseSizeBeforeDelete = workEstimateRateAnalysisRepository.findAll().size();

        // Delete the workEstimateRateAnalysis
        restWorkEstimateRateAnalysisMockMvc
            .perform(delete(ENTITY_API_URL_ID, workEstimateRateAnalysis.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkEstimateRateAnalysis> workEstimateRateAnalysisList = workEstimateRateAnalysisRepository.findAll();
        assertThat(workEstimateRateAnalysisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
