package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RaFormulaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RaFormula.class);
        RaFormula raFormula1 = new RaFormula();
        raFormula1.setId(1L);
        RaFormula raFormula2 = new RaFormula();
        raFormula2.setId(raFormula1.getId());
        assertThat(raFormula1).isEqualTo(raFormula2);
        raFormula2.setId(2L);
        assertThat(raFormula1).isNotEqualTo(raFormula2);
        raFormula1.setId(null);
        assertThat(raFormula1).isNotEqualTo(raFormula2);
    }
}
