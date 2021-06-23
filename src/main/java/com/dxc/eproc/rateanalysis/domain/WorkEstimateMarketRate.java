package com.dxc.eproc.rateanalysis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WorkEstimateMarketRate.
 */
@Entity
@Table(name = "work_estimate_market_rate")
public class WorkEstimateMarketRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "work_estimate_id", nullable = false)
    private Long workEstimateId;

    @NotNull
    @Column(name = "sub_estimate_id", nullable = false)
    private Long subEstimateId;

    @NotNull
    @Column(name = "material_master_id", nullable = false)
    private Long materialMasterId;

    @NotNull
    @Column(name = "difference", precision = 21, scale = 2, nullable = false)
    private BigDecimal difference;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100000000")
    @Column(name = "prevailing_market_rate", precision = 21, scale = 2, nullable = false)
    private BigDecimal prevailingMarketRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkEstimateMarketRate id(Long id) {
        this.id = id;
        return this;
    }

    public Long getWorkEstimateId() {
        return this.workEstimateId;
    }

    public WorkEstimateMarketRate workEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
        return this;
    }

    public void setWorkEstimateId(Long workEstimateId) {
        this.workEstimateId = workEstimateId;
    }

    public Long getSubEstimateId() {
        return this.subEstimateId;
    }

    public WorkEstimateMarketRate subEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
        return this;
    }

    public void setSubEstimateId(Long subEstimateId) {
        this.subEstimateId = subEstimateId;
    }

    public Long getMaterialMasterId() {
        return this.materialMasterId;
    }

    public WorkEstimateMarketRate materialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
        return this;
    }

    public void setMaterialMasterId(Long materialMasterId) {
        this.materialMasterId = materialMasterId;
    }

    public BigDecimal getDifference() {
        return this.difference;
    }

    public WorkEstimateMarketRate difference(BigDecimal difference) {
        this.difference = difference;
        return this;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public BigDecimal getPrevailingMarketRate() {
        return this.prevailingMarketRate;
    }

    public WorkEstimateMarketRate prevailingMarketRate(BigDecimal prevailingMarketRate) {
        this.prevailingMarketRate = prevailingMarketRate;
        return this;
    }

    public void setPrevailingMarketRate(BigDecimal prevailingMarketRate) {
        this.prevailingMarketRate = prevailingMarketRate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkEstimateMarketRate)) {
            return false;
        }
        return id != null && id.equals(((WorkEstimateMarketRate) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkEstimateMarketRate{" +
            "id=" + getId() +
            ", workEstimateId=" + getWorkEstimateId() +
            ", subEstimateId=" + getSubEstimateId() +
            ", materialMasterId=" + getMaterialMasterId() +
            ", difference=" + getDifference() +
            ", prevailingMarketRate=" + getPrevailingMarketRate() +
            "}";
    }
}
