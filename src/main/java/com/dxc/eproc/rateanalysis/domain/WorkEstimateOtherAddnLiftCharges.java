package com.dxc.eproc.rateanalysis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WorkEstimateOtherAddnLiftCharges.
 */
@Entity
@Table(name = "work_estimate_other_addn_lift_charges")
public class WorkEstimateOtherAddnLiftCharges implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "work_estimate_rate_analysis_id", nullable = false)
    private Long workEstimateRateAnalysisId;

    @NotNull
    @Column(name = "notes_master_id", nullable = false)
    private Long notesMasterId;

    @NotNull
    @Column(name = "selected", nullable = false)
    private Boolean selected;

    @NotNull
    @Column(name = "addn_charges", precision = 21, scale = 2, nullable = false)
    private BigDecimal addnCharges;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkEstimateOtherAddnLiftCharges id(Long id) {
        this.id = id;
        return this;
    }

    public Long getWorkEstimateRateAnalysisId() {
        return this.workEstimateRateAnalysisId;
    }

    public WorkEstimateOtherAddnLiftCharges workEstimateRateAnalysisId(Long workEstimateRateAnalysisId) {
        this.workEstimateRateAnalysisId = workEstimateRateAnalysisId;
        return this;
    }

    public void setWorkEstimateRateAnalysisId(Long workEstimateRateAnalysisId) {
        this.workEstimateRateAnalysisId = workEstimateRateAnalysisId;
    }

    public Long getNotesMasterId() {
        return this.notesMasterId;
    }

    public WorkEstimateOtherAddnLiftCharges notesMasterId(Long notesMasterId) {
        this.notesMasterId = notesMasterId;
        return this;
    }

    public void setNotesMasterId(Long notesMasterId) {
        this.notesMasterId = notesMasterId;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public WorkEstimateOtherAddnLiftCharges selected(Boolean selected) {
        this.selected = selected;
        return this;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public BigDecimal getAddnCharges() {
        return this.addnCharges;
    }

    public WorkEstimateOtherAddnLiftCharges addnCharges(BigDecimal addnCharges) {
        this.addnCharges = addnCharges;
        return this;
    }

    public void setAddnCharges(BigDecimal addnCharges) {
        this.addnCharges = addnCharges;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateOtherAddnLiftCharges)) {
            return false;
        }
        return id != null && id.equals(((WorkEstimateOtherAddnLiftCharges) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateOtherAddnLiftCharges{" +
            "id=" + getId() +
            ", workEstimateRateAnalysisId=" + getWorkEstimateRateAnalysisId() +
            ", notesMasterId=" + getNotesMasterId() +
            ", selected='" + getSelected() + "'" +
            ", addnCharges=" + getAddnCharges() +
            "}";
    }
}
