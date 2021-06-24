package com.dxc.eproc.rateanalysis.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateMarketRate} entity.
 */
public class WorkEstimateMarketRateDTO implements Serializable {

    private Long id;

    @NotNull
    private Long workEstimateId;

    @NotNull
    private Long subEstimateId;

    @NotNull
    private Long materialMasterId;

    @NotNull
    private BigDecimal difference;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100000000")
    private BigDecimal prevailingMarketRate;

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

    public Long getSubEstimateId() {
        return subEstimateId;
    }

    public void setSubEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
    }

    public Long getMaterialMasterId() {
        return materialMasterId;
    }

    public void setMaterialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public BigDecimal getPrevailingMarketRate() {
        return prevailingMarketRate;
    }

    public void setPrevailingMarketRate(BigDecimal prevailingMarketRate) {
        this.prevailingMarketRate = prevailingMarketRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateMarketRateDTO)) {
            return false;
        }

        WorkEstimateMarketRateDTO workEstimateMarketRateDTO = (WorkEstimateMarketRateDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workEstimateMarketRateDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateMarketRateDTO{" +
            "id=" + getId() +
            ", workEstimateId=" + getWorkEstimateId() +
            ", subEstimateId=" + getSubEstimateId() +
            ", materialMasterId=" + getMaterialMasterId() +
            ", difference=" + getDifference() +
            ", prevailingMarketRate=" + getPrevailingMarketRate() +
            "}";
    }
}
