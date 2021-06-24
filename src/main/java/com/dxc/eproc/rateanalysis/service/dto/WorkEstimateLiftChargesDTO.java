package com.dxc.eproc.rateanalysis.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateLiftCharges} entity.
 */
public class WorkEstimateLiftChargesDTO implements Serializable {

    private Long id;

    @NotNull
    private Long workEstimateItemId;

    @NotNull
    private Long materialMasterId;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100000000")
    private BigDecimal liftDistance;

    @NotNull
    private BigDecimal liftcharges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkEstimateItemId() {
        return workEstimateItemId;
    }

    public void setWorkEstimateItemId(Long workEstimateItemId) {
        this.workEstimateItemId = workEstimateItemId;
    }

    public Long getMaterialMasterId() {
        return materialMasterId;
    }

    public void setMaterialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
    }

    public BigDecimal getLiftDistance() {
        return liftDistance;
    }

    public void setLiftDistance(BigDecimal liftDistance) {
        this.liftDistance = liftDistance;
    }

    public BigDecimal getLiftcharges() {
        return liftcharges;
    }

    public void setLiftcharges(BigDecimal liftcharges) {
        this.liftcharges = liftcharges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateLiftChargesDTO)) {
            return false;
        }

        WorkEstimateLiftChargesDTO workEstimateLiftChargesDTO = (WorkEstimateLiftChargesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workEstimateLiftChargesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateLiftChargesDTO{" +
            "id=" + getId() +
            ", workEstimateItemId=" + getWorkEstimateItemId() +
            ", materialMasterId=" + getMaterialMasterId() +
            ", liftDistance=" + getLiftDistance() +
            ", liftcharges=" + getLiftcharges() +
            "}";
    }
}
