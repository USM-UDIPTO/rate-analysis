package com.dxc.eproc.rateanalysis.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.dxc.eproc.rateanalysis.domain.RaFormula} entity.
 */
public class RaFormulaDTO implements Serializable {

    private Long id;

    @NotNull
    private Long deptId;

    private Long workTypeId;

    private String formula;

    private String awFormula;

    private String royaltyFormula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Long workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getAwFormula() {
        return awFormula;
    }

    public void setAwFormula(String awFormula) {
        this.awFormula = awFormula;
    }

    public String getRoyaltyFormula() {
        return royaltyFormula;
    }

    public void setRoyaltyFormula(String royaltyFormula) {
        this.royaltyFormula = royaltyFormula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RaFormulaDTO)) {
            return false;
        }

        RaFormulaDTO raFormulaDTO = (RaFormulaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, raFormulaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RaFormulaDTO{" +
            "id=" + getId() +
            ", deptId=" + getDeptId() +
            ", workTypeId=" + getWorkTypeId() +
            ", formula='" + getFormula() + "'" +
            ", awFormula='" + getAwFormula() + "'" +
            ", royaltyFormula='" + getRoyaltyFormula() + "'" +
            "}";
    }
}
