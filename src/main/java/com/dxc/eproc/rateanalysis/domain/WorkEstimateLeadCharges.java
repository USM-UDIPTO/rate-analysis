package com.dxc.eproc.rateanalysis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WorkEstimateLeadCharges.
 */
@Entity
@Table(name = "work_estimate_lead_charges")
public class WorkEstimateLeadCharges implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "work_estimate_id", nullable = false)
    private Long workEstimateId;

    @NotNull
    @Column(name = "cat_work_sor_item_id", nullable = false)
    private Long catWorkSorItemId;

    @NotNull
    @Column(name = "material_master_id", nullable = false)
    private Long materialMasterId;

    @Column(name = "nhwy_road_type_master_id")
    private Long nhwyRoadTypeMasterId;

    @NotNull
    @Column(name = "sub_estimate_id", nullable = false)
    private Long subEstimateId;

    @Size(max = 50)
    @Column(name = "quarry", length = 50)
    private String quarry;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10000")
    @Column(name = "lead_in_m", precision = 21, scale = 2)
    private BigDecimal leadInM;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10000")
    @Column(name = "lead_in_km", precision = 21, scale = 2)
    private BigDecimal leadInKm;

    @Column(name = "lead_charges_m", precision = 21, scale = 2)
    private BigDecimal leadChargesM;

    @Column(name = "lead_charges_km", precision = 21, scale = 2)
    private BigDecimal leadChargesKm;

    @NotNull
    @Column(name = "initial_lead_required_yn", nullable = false)
    private Boolean initialLeadRequiredYn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkEstimateLeadCharges id(Long id) {
        this.id = id;
        return this;
    }

    public Long getWorkEstimateId() {
        return this.workEstimateId;
    }

    public WorkEstimateLeadCharges workEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
        return this;
    }

    public void setWorkEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
    }

    public Long getCatWorkSorItemId() {
        return this.catWorkSorItemId;
    }

    public WorkEstimateLeadCharges catWorkSorItemId(Long catWorkSorItemId) {
        this.catWorkSorItemId = catWorkSorItemId;
        return this;
    }

    public void setCatWorkSorItemId(Long catWorkSorItemId) {
        this.catWorkSorItemId = catWorkSorItemId;
    }

    public Long getMaterialMasterId() {
        return this.materialMasterId;
    }

    public WorkEstimateLeadCharges materialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
        return this;
    }

    public void setMaterialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
    }

    public Long getNhwyRoadTypeMasterId() {
        return this.nhwyRoadTypeMasterId;
    }

    public WorkEstimateLeadCharges nhwyRoadTypeMasterId(Long nhwyRoadTypeMasterId) {
        this.nhwyRoadTypeMasterId = nhwyRoadTypeMasterId;
        return this;
    }

    public void setNhwyRoadTypeMasterId(Long nhwyRoadTypeMasterId) {
        this.nhwyRoadTypeMasterId = nhwyRoadTypeMasterId;
    }

    public Long getSubEstimateId() {
        return this.subEstimateId;
    }

    public WorkEstimateLeadCharges subEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
        return this;
    }

    public void setSubEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
    }

    public String getQuarry() {
        return this.quarry;
    }

    public WorkEstimateLeadCharges quarry(String quarry) {
        this.quarry = quarry;
        return this;
    }

    public void setQuarry(String quarry) {
        this.quarry = quarry;
    }

    public BigDecimal getLeadInM() {
        return this.leadInM;
    }

    public WorkEstimateLeadCharges leadInM(BigDecimal leadInM) {
        this.leadInM = leadInM;
        return this;
    }

    public void setLeadInM(BigDecimal leadInM) {
        this.leadInM = leadInM;
    }

    public BigDecimal getLeadInKm() {
        return this.leadInKm;
    }

    public WorkEstimateLeadCharges leadInKm(BigDecimal leadInKm) {
        this.leadInKm = leadInKm;
        return this;
    }

    public void setLeadInKm(BigDecimal leadInKm) {
        this.leadInKm = leadInKm;
    }

    public BigDecimal getLeadChargesM() {
        return this.leadChargesM;
    }

    public WorkEstimateLeadCharges leadChargesM(BigDecimal leadChargesM) {
        this.leadChargesM = leadChargesM;
        return this;
    }

    public void setLeadChargesM(BigDecimal leadChargesM) {
        this.leadChargesM = leadChargesM;
    }

    public BigDecimal getLeadChargesKm() {
        return this.leadChargesKm;
    }

    public WorkEstimateLeadCharges leadChargesKm(BigDecimal leadChargesKm) {
        this.leadChargesKm = leadChargesKm;
        return this;
    }

    public void setLeadChargesKm(BigDecimal leadChargesKm) {
        this.leadChargesKm = leadChargesKm;
    }

    public Boolean getInitialLeadRequiredYn() {
        return this.initialLeadRequiredYn;
    }

    public WorkEstimateLeadCharges initialLeadRequiredYn(Boolean initialLeadRequiredYn) {
        this.initialLeadRequiredYn = initialLeadRequiredYn;
        return this;
    }

    public void setInitialLeadRequiredYn(Boolean initialLeadRequiredYn) {
        this.initialLeadRequiredYn = initialLeadRequiredYn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateLeadCharges)) {
            return false;
        }
        return id != null && id.equals(((WorkEstimateLeadCharges) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateLeadCharges{" +
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
