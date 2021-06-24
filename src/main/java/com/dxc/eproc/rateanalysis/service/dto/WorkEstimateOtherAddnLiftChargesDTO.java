package com.dxc.eproc.rateanalysis.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.rateanalysis.domain.WorkEstimateOtherAddnLiftCharges} entity.
 */
public class WorkEstimateOtherAddnLiftChargesDTO implements Serializable {

    private Long id;

    @NotNull
    private Long workEstimateRateAnalysisId;

    @NotNull
    private Long notesMasterId;

    @NotNull
    private Boolean selected;

    @NotNull
    private BigDecimal addnCharges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkEstimateRateAnalysisId() {
        return workEstimateRateAnalysisId;
    }

    public void setWorkEstimateRateAnalysisId(Long workEstimateRateAnalysisId) {
        this.workEstimateRateAnalysisId = workEstimateRateAnalysisId;
    }

    public Long getNotesMasterId() {
        return notesMasterId;
    }

    public void setNotesMasterId(Long notesMasterId) {
        this.notesMasterId = notesMasterId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public BigDecimal getAddnCharges() {
        return addnCharges;
    }

    public void setAddnCharges(BigDecimal addnCharges) {
        this.addnCharges = addnCharges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateOtherAddnLiftChargesDTO)) {
            return false;
        }

        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO = (WorkEstimateOtherAddnLiftChargesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workEstimateOtherAddnLiftChargesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateOtherAddnLiftChargesDTO{" +
            "id=" + getId() +
            ", workEstimateRateAnalysisId=" + getWorkEstimateRateAnalysisId() +
            ", notesMasterId=" + getNotesMasterId() +
            ", selected='" + getSelected() + "'" +
            ", addnCharges=" + getAddnCharges() +
            "}";
    }
}
