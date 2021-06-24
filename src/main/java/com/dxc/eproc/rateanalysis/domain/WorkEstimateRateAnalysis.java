package com.dxc.eproc.rateanalysis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WorkEstimateRateAnalysis.
 */
@Entity
@Table(name = "work_estimate_rate_analysis")
public class WorkEstimateRateAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "work_estimate_id", nullable = false)
    private Long workEstimateId;

    @Column(name = "area_weightage_master_id")
    private Long areaWeightageMasterId;

    @Column(name = "area_weightage_circle_id")
    private Long areaWeightageCircleId;

    @Column(name = "area_weightage_percentage", precision = 21, scale = 2)
    private BigDecimal areaWeightagePercentage;

    @Column(name = "sor_financial_year")
    private String sorFinancialYear;

    @NotNull
    @Column(name = "basic_rate", precision = 21, scale = 2, nullable = false)
    private BigDecimal basicRate;

    @NotNull
    @Column(name = "net_rate", precision = 21, scale = 2, nullable = false)
    private BigDecimal netRate;

    @Column(name = "floor_no")
    private Long floorNo;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "contractor_profit_percentage", precision = 21, scale = 2)
    private BigDecimal contractorProfitPercentage;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "overhead_percentage", precision = 21, scale = 2)
    private BigDecimal overheadPercentage;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "tax_percentage", precision = 21, scale = 2)
    private BigDecimal taxPercentage;

    @Column(name = "lift_charges", precision = 21, scale = 2)
    private BigDecimal liftCharges;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "locality_allowance", precision = 21, scale = 2)
    private BigDecimal localityAllowance;

    @Column(name = "employees_cost", precision = 21, scale = 2)
    private BigDecimal employeesCost;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "contingencies", precision = 21, scale = 2)
    private BigDecimal contingencies;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "transportation_cost", precision = 21, scale = 2)
    private BigDecimal transportationCost;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "service_tax", precision = 21, scale = 2)
    private BigDecimal serviceTax;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "provident_fund_charges", precision = 21, scale = 2)
    private BigDecimal providentFundCharges;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "esi_charges", precision = 21, scale = 2)
    private BigDecimal esiCharges;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "idc_charges", precision = 21, scale = 2)
    private BigDecimal idcCharges;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "watch_and_ward_cost", precision = 21, scale = 2)
    private BigDecimal watchAndWardCost;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "insurance_cost", precision = 21, scale = 2)
    private BigDecimal insuranceCost;

    @Column(name = "statutory_charges", precision = 21, scale = 2)
    private BigDecimal statutoryCharges;

    @Column(name = "compensation_cost", precision = 21, scale = 2)
    private BigDecimal compensationCost;

    @NotNull
    @Column(name = "ra_completed_yn", nullable = false)
    private Boolean raCompletedYn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkEstimateRateAnalysis id(Long id) {
        this.id = id;
        return this;
    }

    public Long getWorkEstimateId() {
        return this.workEstimateId;
    }

    public WorkEstimateRateAnalysis workEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
        return this;
    }

    public void setWorkEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
    }

    public Long getAreaWeightageMasterId() {
        return this.areaWeightageMasterId;
    }

    public WorkEstimateRateAnalysis areaWeightageMasterId(Long areaWeightageMasterId) {
        this.areaWeightageMasterId = areaWeightageMasterId;
        return this;
    }

    public void setAreaWeightageMasterId(Long areaWeightageMasterId) {
        this.areaWeightageMasterId = areaWeightageMasterId;
    }

    public Long getAreaWeightageCircleId() {
        return this.areaWeightageCircleId;
    }

    public WorkEstimateRateAnalysis areaWeightageCircleId(Long areaWeightageCircleId) {
        this.areaWeightageCircleId = areaWeightageCircleId;
        return this;
    }

    public void setAreaWeightageCircleId(Long areaWeightageCircleId) {
        this.areaWeightageCircleId = areaWeightageCircleId;
    }

    public BigDecimal getAreaWeightagePercentage() {
        return this.areaWeightagePercentage;
    }

    public WorkEstimateRateAnalysis areaWeightagePercentage(BigDecimal areaWeightagePercentage) {
        this.areaWeightagePercentage = areaWeightagePercentage;
        return this;
    }

    public void setAreaWeightagePercentage(BigDecimal areaWeightagePercentage) {
        this.areaWeightagePercentage = areaWeightagePercentage;
    }

    public String getSorFinancialYear() {
        return this.sorFinancialYear;
    }

    public WorkEstimateRateAnalysis sorFinancialYear(String sorFinancialYear) {
        this.sorFinancialYear = sorFinancialYear;
        return this;
    }

    public void setSorFinancialYear(String sorFinancialYear) {
        this.sorFinancialYear = sorFinancialYear;
    }

    public BigDecimal getBasicRate() {
        return this.basicRate;
    }

    public WorkEstimateRateAnalysis basicRate(BigDecimal basicRate) {
        this.basicRate = basicRate;
        return this;
    }

    public void setBasicRate(BigDecimal basicRate) {
        this.basicRate = basicRate;
    }

    public BigDecimal getNetRate() {
        return this.netRate;
    }

    public WorkEstimateRateAnalysis netRate(BigDecimal netRate) {
        this.netRate = netRate;
        return this;
    }

    public void setNetRate(BigDecimal netRate) {
        this.netRate = netRate;
    }

    public Long getFloorNo() {
        return this.floorNo;
    }

    public WorkEstimateRateAnalysis floorNo(Long floorNo) {
        this.floorNo = floorNo;
        return this;
    }

    public void setFloorNo(Long floorNo) {
        this.floorNo = floorNo;
    }

    public BigDecimal getContractorProfitPercentage() {
        return this.contractorProfitPercentage;
    }

    public WorkEstimateRateAnalysis contractorProfitPercentage(BigDecimal contractorProfitPercentage) {
        this.contractorProfitPercentage = contractorProfitPercentage;
        return this;
    }

    public void setContractorProfitPercentage(BigDecimal contractorProfitPercentage) {
        this.contractorProfitPercentage = contractorProfitPercentage;
    }

    public BigDecimal getOverheadPercentage() {
        return this.overheadPercentage;
    }

    public WorkEstimateRateAnalysis overheadPercentage(BigDecimal overheadPercentage) {
        this.overheadPercentage = overheadPercentage;
        return this;
    }

    public void setOverheadPercentage(BigDecimal overheadPercentage) {
        this.overheadPercentage = overheadPercentage;
    }

    public BigDecimal getTaxPercentage() {
        return this.taxPercentage;
    }

    public WorkEstimateRateAnalysis taxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
        return this;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public BigDecimal getLiftCharges() {
        return this.liftCharges;
    }

    public WorkEstimateRateAnalysis liftCharges(BigDecimal liftCharges) {
        this.liftCharges = liftCharges;
        return this;
    }

    public void setLiftCharges(BigDecimal liftCharges) {
        this.liftCharges = liftCharges;
    }

    public BigDecimal getLocalityAllowance() {
        return this.localityAllowance;
    }

    public WorkEstimateRateAnalysis localityAllowance(BigDecimal localityAllowance) {
        this.localityAllowance = localityAllowance;
        return this;
    }

    public void setLocalityAllowance(BigDecimal localityAllowance) {
        this.localityAllowance = localityAllowance;
    }

    public BigDecimal getEmployeesCost() {
        return this.employeesCost;
    }

    public WorkEstimateRateAnalysis employeesCost(BigDecimal employeesCost) {
        this.employeesCost = employeesCost;
        return this;
    }

    public void setEmployeesCost(BigDecimal employeesCost) {
        this.employeesCost = employeesCost;
    }

    public BigDecimal getContingencies() {
        return this.contingencies;
    }

    public WorkEstimateRateAnalysis contingencies(BigDecimal contingencies) {
        this.contingencies = contingencies;
        return this;
    }

    public void setContingencies(BigDecimal contingencies) {
        this.contingencies = contingencies;
    }

    public BigDecimal getTransportationCost() {
        return this.transportationCost;
    }

    public WorkEstimateRateAnalysis transportationCost(BigDecimal transportationCost) {
        this.transportationCost = transportationCost;
        return this;
    }

    public void setTransportationCost(BigDecimal transportationCost) {
        this.transportationCost = transportationCost;
    }

    public BigDecimal getServiceTax() {
        return this.serviceTax;
    }

    public WorkEstimateRateAnalysis serviceTax(BigDecimal serviceTax) {
        this.serviceTax = serviceTax;
        return this;
    }

    public void setServiceTax(BigDecimal serviceTax) {
        this.serviceTax = serviceTax;
    }

    public BigDecimal getProvidentFundCharges() {
        return this.providentFundCharges;
    }

    public WorkEstimateRateAnalysis providentFundCharges(BigDecimal providentFundCharges) {
        this.providentFundCharges = providentFundCharges;
        return this;
    }

    public void setProvidentFundCharges(BigDecimal providentFundCharges) {
        this.providentFundCharges = providentFundCharges;
    }

    public BigDecimal getEsiCharges() {
        return this.esiCharges;
    }

    public WorkEstimateRateAnalysis esiCharges(BigDecimal esiCharges) {
        this.esiCharges = esiCharges;
        return this;
    }

    public void setEsiCharges(BigDecimal esiCharges) {
        this.esiCharges = esiCharges;
    }

    public BigDecimal getIdcCharges() {
        return this.idcCharges;
    }

    public WorkEstimateRateAnalysis idcCharges(BigDecimal idcCharges) {
        this.idcCharges = idcCharges;
        return this;
    }

    public void setIdcCharges(BigDecimal idcCharges) {
        this.idcCharges = idcCharges;
    }

    public BigDecimal getWatchAndWardCost() {
        return this.watchAndWardCost;
    }

    public WorkEstimateRateAnalysis watchAndWardCost(BigDecimal watchAndWardCost) {
        this.watchAndWardCost = watchAndWardCost;
        return this;
    }

    public void setWatchAndWardCost(BigDecimal watchAndWardCost) {
        this.watchAndWardCost = watchAndWardCost;
    }

    public BigDecimal getInsuranceCost() {
        return this.insuranceCost;
    }

    public WorkEstimateRateAnalysis insuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
        return this;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public BigDecimal getStatutoryCharges() {
        return this.statutoryCharges;
    }

    public WorkEstimateRateAnalysis statutoryCharges(BigDecimal statutoryCharges) {
        this.statutoryCharges = statutoryCharges;
        return this;
    }

    public void setStatutoryCharges(BigDecimal statutoryCharges) {
        this.statutoryCharges = statutoryCharges;
    }

    public BigDecimal getCompensationCost() {
        return this.compensationCost;
    }

    public WorkEstimateRateAnalysis compensationCost(BigDecimal compensationCost) {
        this.compensationCost = compensationCost;
        return this;
    }

    public void setCompensationCost(BigDecimal compensationCost) {
        this.compensationCost = compensationCost;
    }

    public Boolean getRaCompletedYn() {
        return this.raCompletedYn;
    }

    public WorkEstimateRateAnalysis raCompletedYn(Boolean raCompletedYn) {
        this.raCompletedYn = raCompletedYn;
        return this;
    }

    public void setRaCompletedYn(Boolean raCompletedYn) {
        this.raCompletedYn = raCompletedYn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateRateAnalysis)) {
            return false;
        }
        return id != null && id.equals(((WorkEstimateRateAnalysis) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateRateAnalysis{" +
            "id=" + getId() +
            ", workEstimateId=" + getWorkEstimateId() +
            ", areaWeightageMasterId=" + getAreaWeightageMasterId() +
            ", areaWeightageCircleId=" + getAreaWeightageCircleId() +
            ", areaWeightagePercentage=" + getAreaWeightagePercentage() +
            ", sorFinancialYear='" + getSorFinancialYear() + "'" +
            ", basicRate=" + getBasicRate() +
            ", netRate=" + getNetRate() +
            ", floorNo=" + getFloorNo() +
            ", contractorProfitPercentage=" + getContractorProfitPercentage() +
            ", overheadPercentage=" + getOverheadPercentage() +
            ", taxPercentage=" + getTaxPercentage() +
            ", liftCharges=" + getLiftCharges() +
            ", localityAllowance=" + getLocalityAllowance() +
            ", employeesCost=" + getEmployeesCost() +
            ", contingencies=" + getContingencies() +
            ", transportationCost=" + getTransportationCost() +
            ", serviceTax=" + getServiceTax() +
            ", providentFundCharges=" + getProvidentFundCharges() +
            ", esiCharges=" + getEsiCharges() +
            ", idcCharges=" + getIdcCharges() +
            ", watchAndWardCost=" + getWatchAndWardCost() +
            ", insuranceCost=" + getInsuranceCost() +
            ", statutoryCharges=" + getStatutoryCharges() +
            ", compensationCost=" + getCompensationCost() +
            ", raCompletedYn='" + getRaCompletedYn() + "'" +
            "}";
    }
}
