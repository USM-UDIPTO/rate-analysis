package com.dxc.eproc.rateanalysis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateLiftChargesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateLiftChargesDTO.class);
        WorkEstimateLiftChargesDTO workEstimateLiftChargesDTO1 = new WorkEstimateLiftChargesDTO();
        workEstimateLiftChargesDTO1.setId(1L);
        WorkEstimateLiftChargesDTO workEstimateLiftChargesDTO2 = new WorkEstimateLiftChargesDTO();
        assertThat(workEstimateLiftChargesDTO1).isNotEqualTo(workEstimateLiftChargesDTO2);
        workEstimateLiftChargesDTO2.setId(workEstimateLiftChargesDTO1.getId());
        assertThat(workEstimateLiftChargesDTO1).isEqualTo(workEstimateLiftChargesDTO2);
        workEstimateLiftChargesDTO2.setId(2L);
        assertThat(workEstimateLiftChargesDTO1).isNotEqualTo(workEstimateLiftChargesDTO2);
        workEstimateLiftChargesDTO1.setId(null);
        assertThat(workEstimateLiftChargesDTO1).isNotEqualTo(workEstimateLiftChargesDTO2);
    }
}
