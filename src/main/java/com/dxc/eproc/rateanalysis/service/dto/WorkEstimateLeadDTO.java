package com.dxc.eproc.rateanalysis.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateLead} entity.
 */
public class WorkEstimateLeadDTO implements Serializable {

    private Long id;

    @NotNull
    private Long workEstimateId;

    @NotNull
    private Long catWorkSorItemId;

    @NotNull
    private Long materialMasterId;

    private Long nhwyRoadTypeMasterId;

    @NotNull
    private Long subEstimateId;

    @Size(max = 50)
    private String quarry;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10000")
    private BigDecimal leadInM;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10000")
    private BigDecimal leadInKm;

    private BigDecimal leadChargesM;

    private BigDecimal leadChargesKm;

    @NotNull
    private Boolean initialLeadRequiredYn;

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

    public Long getCatWorkSorItemId() {
        return catWorkSorItemId;
    }

    public void setCatWorkSorItemId(Long catWorkSorItemId) {
        this.catWorkSorItemId = catWorkSorItemId;
    }

    public Long getMaterialMasterId() {
        return materialMasterId;
    }

    public void setMaterialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
    }

    public Long getNhwyRoadTypeMasterId() {
        return nhwyRoadTypeMasterId;
    }

    public void setNhwyRoadTypeMasterId(Long nhwyRoadTypeMasterId) {
        this.nhwyRoadTypeMasterId = nhwyRoadTypeMasterId;
    }

    public Long getSubEstimateId() {
        return subEstimateId;
    }

    public void setSubEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
    }

    public String getQuarry() {
        return quarry;
    }

    public void setQuarry(String quarry) {
        this.quarry = quarry;
    }

    public BigDecimal getLeadInM() {
        return leadInM;
    }

    public void setLeadInM(BigDecimal leadInM) {
        this.leadInM = leadInM;
    }

    public BigDecimal getLeadInKm() {
        return leadInKm;
    }

    public void setLeadInKm(BigDecimal leadInKm) {
        this.leadInKm = leadInKm;
    }

    public BigDecimal getLeadChargesM() {
        return leadChargesM;
    }

    public void setLeadChargesM(BigDecimal leadChargesM) {
        this.leadChargesM = leadChargesM;
    }

    public BigDecimal getLeadChargesKm() {
        return leadChargesKm;
    }

    public void setLeadChargesKm(BigDecimal leadChargesKm) {
        this.leadChargesKm = leadChargesKm;
    }

    public Boolean getInitialLeadRequiredYn() {
        return initialLeadRequiredYn;
    }

    public void setInitialLeadRequiredYn(Boolean initialLeadRequiredYn) {
        this.initialLeadRequiredYn = initialLeadRequiredYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateLeadDTO)) {
            return false;
        }

        WorkEstimateLeadDTO workEstimateLeadDTO = (WorkEstimateLeadDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workEstimateLeadDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateLeadDTO{" +
            "id=" + getId() +
            ", workEstimateId=" + getWorkEstimateId() +
            ", catWorkSorItemId=" + getCatWorkSorItemId() +
            ", materialMasterId=" + getMaterialMasterId() +
            ", nhwyRoadTypeMasterId=" + getNhwyRoadTypeMasterId() +
            ", subEstimateId=" + getSubEstimateId() +
            ", quarry='" + getQuarry() + "'" +
            ", leadInM=" + getLeadInM() +
            ", leadInKm=" + getLeadInKm() +
            ", leadChargesM=" + getLeadChargesM() +
            ", leadChargesKm=" + getLeadChargesKm() +
            ", initialLeadRequiredYn='" + getInitialLeadRequiredYn() + "'" +
            "}";
    }
}
