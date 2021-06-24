package com.dxc.eproc.rateanalysis.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateRateAnalysis} entity.
 */
public class WorkEstimateRateAnalysisDTO implements Serializable {

    private Long id;

    @NotNull
    private Long workEstimateId;

    private Long areaWeightageMasterId;

    private Long areaWeightageCircleId;

    private BigDecimal areaWeightagePercentage;

    private String sorFinancialYear;

    @NotNull
    private BigDecimal basicRate;

    @NotNull
    private BigDecimal netRate;

    private Long floorNo;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal contractorProfitPercentage;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal overheadPercentage;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal taxPercentage;

    private BigDecimal liftCharges;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal localityAllowance;

    private BigDecimal employeesCost;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal contingencies;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal transportationCost;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal serviceTax;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal providentFundCharges;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal esiCharges;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal idcCharges;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal watchAndWardCost;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal insuranceCost;

    private BigDecimal statutoryCharges;

    private BigDecimal compensationCost;

    @NotNull
    private Boolean raCompletedYn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkEstimateId() {
        return workEstimateId;
    }

    public void setWorkEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
    }

    public Long getAreaWeightageMasterId() {
        return areaWeightageMasterId;
    }

    public void setAreaWeightageMasterId(Long areaWeightageMasterId) {
        this.areaWeightageMasterId = areaWeightageMasterId;
    }

    public Long getAreaWeightageCircleId() {
        return areaWeightageCircleId;
    }

    public void setAreaWeightageCircleId(Long areaWeightageCircleId) {
        this.areaWeightageCircleId = areaWeightageCircleId;
    }

    public BigDecimal getAreaWeightagePercentage() {
        return areaWeightagePercentage;
    }

    public void setAreaWeightagePercentage(BigDecimal areaWeightagePercentage) {
        this.areaWeightagePercentage = areaWeightagePercentage;
    }

    public String getSorFinancialYear() {
        return sorFinancialYear;
    }

    public void setSorFinancialYear(String sorFinancialYear) {
        this.sorFinancialYear = sorFinancialYear;
    }

    public BigDecimal getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(BigDecimal basicRate) {
        this.basicRate = basicRate;
    }

    public BigDecimal getNetRate() {
        return netRate;
    }

    public void setNetRate(BigDecimal netRate) {
        this.netRate = netRate;
    }

    public Long getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Long floorNo) {
        this.floorNo = floorNo;
    }

    public BigDecimal getContractorProfitPercentage() {
        return contractorProfitPercentage;
    }

    public void setContractorProfitPercentage(BigDecimal contractorProfitPercentage) {
        this.contractorProfitPercentage = contractorProfitPercentage;
    }

    public BigDecimal getOverheadPercentage() {
        return overheadPercentage;
    }

    public void setOverheadPercentage(BigDecimal overheadPercentage) {
        this.overheadPercentage = overheadPercentage;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public BigDecimal getLiftCharges() {
        return liftCharges;
    }

    public void setLiftCharges(BigDecimal liftCharges) {
        this.liftCharges = liftCharges;
    }

    public BigDecimal getLocalityAllowance() {
        return localityAllowance;
    }

    public void setLocalityAllowance(BigDecimal localityAllowance) {
        this.localityAllowance = localityAllowance;
    }

    public BigDecimal getEmployeesCost() {
        return employeesCost;
    }

    public void setEmployeesCost(BigDecimal employeesCost) {
        this.employeesCost = employeesCost;
    }

    public BigDecimal getContingencies() {
        return contingencies;
    }

    public void setContingencies(BigDecimal contingencies) {
        this.contingencies = contingencies;
    }

    public BigDecimal getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(BigDecimal transportationCost) {
        this.transportationCost = transportationCost;
    }

    public BigDecimal getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(BigDecimal serviceTax) {
        this.serviceTax = serviceTax;
    }

    public BigDecimal getProvidentFundCharges() {
        return providentFundCharges;
    }

    public void setProvidentFundCharges(BigDecimal providentFundCharges) {
        this.providentFundCharges = providentFundCharges;
    }

    public BigDecimal getEsiCharges() {
        return esiCharges;
    }

    public void setEsiCharges(BigDecimal esiCharges) {
        this.esiCharges = esiCharges;
    }

    public BigDecimal getIdcCharges() {
        return idcCharges;
    }

    public void setIdcCharges(BigDecimal idcCharges) {
        this.idcCharges = idcCharges;
    }

    public BigDecimal getWatchAndWardCost() {
        return watchAndWardCost;
    }

    public void setWatchAndWardCost(BigDecimal watchAndWardCost) {
        this.watchAndWardCost = watchAndWardCost;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public BigDecimal getStatutoryCharges() {
        return statutoryCharges;
    }

    public void setStatutoryCharges(BigDecimal statutoryCharges) {
        this.statutoryCharges = statutoryCharges;
    }

    public BigDecimal getCompensationCost() {
        return compensationCost;
    }

    public void setCompensationCost(BigDecimal compensationCost) {
        this.compensationCost = compensationCost;
    }

    public Boolean getRaCompletedYn() {
        return raCompletedYn;
    }

    public void setRaCompletedYn(Boolean raCompletedYn) {
        this.raCompletedYn = raCompletedYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateRateAnalysisDTO)) {
            return false;
        }

        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO = (WorkEstimateRateAnalysisDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workEstimateRateAnalysisDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateRateAnalysisDTO{" +
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
