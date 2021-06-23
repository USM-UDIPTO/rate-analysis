package com.dxc.eproc.rateanalysis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WorkEstimateRoyalty.
 */
@Entity
@Table(name = "work_estimate_royalty")
public class WorkEstimateRoyalty implements Serializable {

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

    @NotNull
    @Column(name = "sub_estimate_id", nullable = false)
    private Long subEstimateId;

    @NotNull
    @Column(name = "royalty_rate_master_id", nullable = false)
    private Long royaltyRateMasterId;

    @NotNull
    @Column(name = "sr_royalty_charges", precision = 21, scale = 2, nullable = false)
    private BigDecimal srRoyaltyCharges;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100000000")
    @Column(name = "prevailing_royalty_charges", precision = 21, scale = 2, nullable = false)
    private BigDecimal prevailingRoyaltyCharges;

    @NotNull
    @Column(name = "density_factor", precision = 21, scale = 2, nullable = false)
    private BigDecimal densityFactor;

    @Column(name = "difference", precision = 21, scale = 2)
    private BigDecimal difference;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkEstimateRoyalty id(Long id) {
        this.id = id;
        return this;
    }

    public Long getWorkEstimateId() {
        return this.workEstimateId;
    }

    public WorkEstimateRoyalty workEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
        return this;
    }

    public void setWorkEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
    }

    public Long getCatWorkSorItemId() {
        return this.catWorkSorItemId;
    }

    public WorkEstimateRoyalty catWorkSorItemId(Long catWorkSorItemId) {
        this.catWorkSorItemId = catWorkSorItemId;
        return this;
    }

    public void setCatWorkSorItemId(Long catWorkSorItemId) {
        this.catWorkSorItemId = catWorkSorItemId;
    }

    public Long getMaterialMasterId() {
        return this.materialMasterId;
    }

    public WorkEstimateRoyalty materialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
        return this;
    }

    public void setMaterialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
    }

    public Long getSubEstimateId() {
        return this.subEstimateId;
    }

    public WorkEstimateRoyalty subEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
        return this;
    }

    public void setSubEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
    }

    public Long getRoyaltyRateMasterId() {
        return this.royaltyRateMasterId;
    }

    public WorkEstimateRoyalty royaltyRateMasterId(Long royaltyRateMasterId) {
        this.royaltyRateMasterId = royaltyRateMasterId;
        return this;
    }

    public void setRoyaltyRateMasterId(Long royaltyRateMasterId) {
        this.royaltyRateMasterId = royaltyRateMasterId;
    }

    public BigDecimal getSrRoyaltyCharges() {
        return this.srRoyaltyCharges;
    }

    public WorkEstimateRoyalty srRoyaltyCharges(BigDecimal srRoyaltyCharges) {
        this.srRoyaltyCharges = srRoyaltyCharges;
        return this;
    }

    public void setSrRoyaltyCharges(BigDecimal srRoyaltyCharges) {
        this.srRoyaltyCharges = srRoyaltyCharges;
    }

    public BigDecimal getPrevailingRoyaltyCharges() {
        return this.prevailingRoyaltyCharges;
    }

    public WorkEstimateRoyalty prevailingRoyaltyCharges(BigDecimal prevailingRoyaltyCharges) {
        this.prevailingRoyaltyCharges = prevailingRoyaltyCharges;
        return this;
    }

    public void setPrevailingRoyaltyCharges(BigDecimal prevailingRoyaltyCharges) {
        this.prevailingRoyaltyCharges = prevailingRoyaltyCharges;
    }

    public BigDecimal getDensityFactor() {
        return this.densityFactor;
    }

    public WorkEstimateRoyalty densityFactor(BigDecimal densityFactor) {
        this.densityFactor = densityFactor;
        return this;
    }

    public void setDensityFactor(BigDecimal densityFactor) {
        this.densityFactor = densityFactor;
    }

    public BigDecimal getDifference() {
        return this.difference;
    }

    public WorkEstimateRoyalty difference(BigDecimal difference) {
        this.difference = difference;
        return this;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateRoyalty)) {
            return false;
        }
        return id != null && id.equals(((WorkEstimateRoyalty) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateRoyalty{" +
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
