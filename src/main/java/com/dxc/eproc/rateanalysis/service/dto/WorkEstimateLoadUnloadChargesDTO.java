package com.dxc.eproc.rateanalysis.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateLoadUnloadCharges} entity.
 */
public class WorkEstimateLoadUnloadChargesDTO implements Serializable {

    private Long id;

    @NotNull
    private Long workEstimateId;

    @NotNull
    private Long catWorkSorItemId;

    @NotNull
    private Long materialMasterId;

    @NotNull
    private Long loadUnloadRateMasterId;

    @NotNull
    private Long subEstimateId;

    @NotNull
    private Boolean selectedLoadCharges;

    @NotNull
    private BigDecimal loadingCharges;

    @NotNull
    private Boolean selectedUnloadCharges;

    @NotNull
    private BigDecimal unloadingCharges;

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

    public Long getLoadUnloadRateMasterId() {
        return loadUnloadRateMasterId;
    }

    public void setLoadUnloadRateMasterId(Long loadUnloadRateMasterId) {
        this.loadUnloadRateMasterId = loadUnloadRateMasterId;
    }

    public Long getSubEstimateId() {
        return subEstimateId;
    }

    public void setSubEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
    }

    public Boolean getSelectedLoadCharges() {
        return selectedLoadCharges;
    }

    public void setSelectedLoadCharges(Boolean selectedLoadCharges) {
        this.selectedLoadCharges = selectedLoadCharges;
    }

    public BigDecimal getLoadingCharges() {
        return loadingCharges;
    }

    public void setLoadingCharges(BigDecimal loadingCharges) {
        this.loadingCharges = loadingCharges;
    }

    public Boolean getSelectedUnloadCharges() {
        return selectedUnloadCharges;
    }

    public void setSelectedUnloadCharges(Boolean selectedUnloadCharges) {
        this.selectedUnloadCharges = selectedUnloadCharges;
    }

    public BigDecimal getUnloadingCharges() {
        return unloadingCharges;
    }

    public void setUnloadingCharges(BigDecimal unloadingCharges) {
        this.unloadingCharges = unloadingCharges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateLoadUnloadChargesDTO)) {
            return false;
        }

        WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO = (WorkEstimateLoadUnloadChargesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workEstimateLoadUnloadChargesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateLoadUnloadChargesDTO{" +
            "id=" + getId() +
            ", workEstimateId=" + getWorkEstimateId() +
            ", catWorkSorItemId=" + getCatWorkSorItemId() +
            ", materialMasterId=" + getMaterialMasterId() +
            ", loadUnloadRateMasterId=" + getLoadUnloadRateMasterId() +
            ", subEstimateId=" + getSubEstimateId() +
            ", selectedLoadCharges='" + getSelectedLoadCharges() + "'" +
            ", loadingCharges=" + getLoadingCharges() +
            ", selectedUnloadCharges='" + getSelectedUnloadCharges() + "'" +
            ", unloadingCharges=" + getUnloadingCharges() +
            "}";
    }
}
