package com.dxc.eproc.rateanalysis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RaFormulaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RaFormulaDTO.class);
        RaFormulaDTO raFormulaDTO1 = new RaFormulaDTO();
        raFormulaDTO1.setId(1L);
        RaFormulaDTO raFormulaDTO2 = new RaFormulaDTO();
        assertThat(raFormulaDTO1).isNotEqualTo(raFormulaDTO2);
        raFormulaDTO2.setId(raFormulaDTO1.getId());
        assertThat(raFormulaDTO1).isEqualTo(raFormulaDTO2);
        raFormulaDTO2.setId(2L);
        assertThat(raFormulaDTO1).isNotEqualTo(raFormulaDTO2);
        raFormulaDTO1.setId(null);
        assertThat(raFormulaDTO1).isNotEqualTo(raFormulaDTO2);
    }
}
