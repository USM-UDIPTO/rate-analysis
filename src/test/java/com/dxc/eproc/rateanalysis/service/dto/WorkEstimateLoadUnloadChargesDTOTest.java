package com.dxc.eproc.rateanalysis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateLoadUnloadChargesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateLoadUnloadChargesDTO.class);
        WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO1 = new WorkEstimateLoadUnloadChargesDTO();
        workEstimateLoadUnloadChargesDTO1.setId(1L);
        WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO2 = new WorkEstimateLoadUnloadChargesDTO();
        assertThat(workEstimateLoadUnloadChargesDTO1).isNotEqualTo(workEstimateLoadUnloadChargesDTO2);
        workEstimateLoadUnloadChargesDTO2.setId(workEstimateLoadUnloadChargesDTO1.getId());
        assertThat(workEstimateLoadUnloadChargesDTO1).isEqualTo(workEstimateLoadUnloadChargesDTO2);
        workEstimateLoadUnloadChargesDTO2.setId(2L);
        assertThat(workEstimateLoadUnloadChargesDTO1).isNotEqualTo(workEstimateLoadUnloadChargesDTO2);
        workEstimateLoadUnloadChargesDTO1.setId(null);
        assertThat(workEstimateLoadUnloadChargesDTO1).isNotEqualTo(workEstimateLoadUnloadChargesDTO2);
    }
}
