package com.dxc.eproc.rateanalysis.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A RaFormula.
 */
@Entity
@Table(name = "ra_formula")
public class RaFormula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "dept_id", nullable = false)
    private Long deptId;

    @Column(name = "work_type_id")
    private Long workTypeId;

    @Column(name = "formula")
    private String formula;

    @Column(name = "aw_formula")
    private String awFormula;

    @Column(name = "royalty_formula")
    private String royaltyFormula;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RaFormula id(Long id) {
        this.id = id;
        return this;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public RaFormula deptId(Long deptId) {
        this.deptId = deptId;
        return this;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getWorkTypeId() {
        return this.workTypeId;
    }

    public RaFormula workTypeId(Long workTypeId) {
        this.workTypeId = workTypeId;
        return this;
    }

    public void setWorkTypeId(Long workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getFormula() {
        return this.formula;
    }

    public RaFormula formula(String formula) {
        this.formula = formula;
        return this;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getAwFormula() {
        return this.awFormula;
    }

    public RaFormula awFormula(String awFormula) {
        this.awFormula = awFormula;
        return this;
    }

    public void setAwFormula(String awFormula) {
        this.awFormula = awFormula;
    }

    public String getRoyaltyFormula() {
        return this.royaltyFormula;
    }

    public RaFormula royaltyFormula(String royaltyFormula) {
        this.royaltyFormula = royaltyFormula;
        return this;
    }

    public void setRoyaltyFormula(String royaltyFormula) {
        this.royaltyFormula = royaltyFormula;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RaFormula)) {
            return false;
        }
        return id != null && id.equals(((RaFormula) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RaFormula{" +
            "id=" + getId() +
            ", deptId=" + getDeptId() +
            ", workTypeId=" + getWorkTypeId() +
            ", formula='" + getFormula() + "'" +
            ", awFormula='" + getAwFormula() + "'" +
            ", royaltyFormula='" + getRoyaltyFormula() + "'" +
            "}";
    }
}
