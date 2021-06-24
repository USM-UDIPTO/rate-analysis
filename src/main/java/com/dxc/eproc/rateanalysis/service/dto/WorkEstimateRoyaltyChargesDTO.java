package com.dxc.eproc.rateanalysis.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyaltyCharges} entity.
 */
public class WorkEstimateRoyaltyChargesDTO implements Serializable {

    private Long id;

    @NotNull
    private Long workEstimateId;

    @NotNull
    private Long catWorkSorItemId;

    @NotNull
    private Long materialMasterId;

    @NotNull
    private Long subEstimateId;

    @NotNull
    private Long royaltyRateMasterId;

    @NotNull
    private BigDecimal srRoyaltyCharges;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100000000")
    private BigDecimal prevailingRoyaltyCharges;

    @NotNull
    private BigDecimal densityFactor;

    private BigDecimal difference;

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

    public Long getSubEstimateId() {
        return subEstimateId;
    }

    public void setSubEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
    }

    public Long getRoyaltyRateMasterId() {
        return royaltyRateMasterId;
    }

    public void setRoyaltyRateMasterId(Long royaltyRateMasterId) {
        this.royaltyRateMasterId = royaltyRateMasterId;
    }

    public BigDecimal getSrRoyaltyCharges() {
        return srRoyaltyCharges;
    }

    public void setSrRoyaltyCharges(BigDecimal srRoyaltyCharges) {
        this.srRoyaltyCharges = srRoyaltyCharges;
    }

    public BigDecimal getPrevailingRoyaltyCharges() {
        return prevailingRoyaltyCharges;
    }

    public void setPrevailingRoyaltyCharges(BigDecimal prevailingRoyaltyCharges) {
        this.prevailingRoyaltyCharges = prevailingRoyaltyCharges;
    }

    public BigDecimal getDensityFactor() {
        return densityFactor;
    }

    public void setDensityFactor(BigDecimal densityFactor) {
        this.densityFactor = densityFactor;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateRoyaltyChargesDTO)) {
            return false;
        }

        WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO = (WorkEstimateRoyaltyChargesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workEstimateRoyaltyChargesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateRoyaltyChargesDTO{" +
            "id=" + getId() +
            ", workEstimateId=" + getWorkEstimateId() +
            ", catWorkSorItemId=" + getCatWorkSorItemId() +
            ", materialMasterId=" + getMaterialMasterId() +
            ", subEstimateId=" + getSubEstimateId() +
            ", royaltyRateMasterId=" + getRoyaltyRateMasterId() +
            ", srRoyaltyCharges=" + getSrRoyaltyCharges() +
            ", prevailingRoyaltyCharges=" + getPrevailingRoyaltyCharges() +
            ", densityFactor=" + getDensityFactor() +
            ", difference=" + getDifference() +
            "}";
    }
}
