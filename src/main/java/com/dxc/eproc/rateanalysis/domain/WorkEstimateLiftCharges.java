package com.dxc.eproc.rateanalysis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WorkEstimateLiftCharges.
 */
@Entity
@Table(name = "work_estimate_lift_charges")
public class WorkEstimateLiftCharges implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "work_estimate_item_id", nullable = false)
    private Long workEstimateItemId;

    @NotNull
    @Column(name = "material_master_id", nullable = false)
    private Long materialMasterId;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100000000")
    @Column(name = "lift_distance", precision = 21, scale = 2, nullable = false)
    private BigDecimal liftDistance;

    @NotNull
    @Column(name = "liftcharges", precision = 21, scale = 2, nullable = false)
    private BigDecimal liftcharges;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkEstimateLiftCharges id(Long id) {
        this.id = id;
        return this;
    }

    public Long getWorkEstimateItemId() {
        return this.workEstimateItemId;
    }

    public WorkEstimateLiftCharges workEstimateItemId(Long workEstimateItemId) {
        this.workEstimateItemId = workEstimateItemId;
        return this;
    }

    public void setWorkEstimateItemId(Long workEstimateItemId) {
        this.workEstimateItemId = workEstimateItemId;
    }

    public Long getMaterialMasterId() {
        return this.materialMasterId;
    }

    public WorkEstimateLiftCharges materialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
        return this;
    }

    public void setMaterialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
    }

    public BigDecimal getLiftDistance() {
        return this.liftDistance;
    }

    public WorkEstimateLiftCharges liftDistance(BigDecimal liftDistance) {
        this.liftDistance = liftDistance;
        return this;
    }

    public void setLiftDistance(BigDecimal liftDistance) {
        this.liftDistance = liftDistance;
    }

    public BigDecimal getLiftcharges() {
        return this.liftcharges;
    }

    public WorkEstimateLiftCharges liftcharges(BigDecimal liftcharges) {
        this.liftcharges = liftcharges;
        return this;
    }

    public void setLiftcharges(BigDecimal liftcharges) {
        this.liftcharges = liftcharges;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateLiftCharges)) {
            return false;
        }
        return id != null && id.equals(((WorkEstimateLiftCharges) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateLiftCharges{" +
            "id=" + getId() +
            ", workEstimateItemId=" + getWorkEstimateItemId() +
            ", materialMasterId=" + getMaterialMasterId() +
            ", liftDistance=" + getLiftDistance() +
            ", liftcharges=" + getLiftcharges() +
            "}";
    }
}
