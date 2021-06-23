package com.dxc.eproc.rateanalysis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateOtherAddnLiftChargesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateOtherAddnLiftChargesDTO.class);
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO1 = new WorkEstimateOtherAddnLiftChargesDTO();
        workEstimateOtherAddnLiftChargesDTO1.setId(1L);
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO2 = new WorkEstimateOtherAddnLiftChargesDTO();
        assertThat(workEstimateOtherAddnLiftChargesDTO1).isNotEqualTo(workEstimateOtherAddnLiftChargesDTO2);
        workEstimateOtherAddnLiftChargesDTO2.setId(workEstimateOtherAddnLiftChargesDTO1.getId());
        assertThat(workEstimateOtherAddnLiftChargesDTO1).isEqualTo(workEstimateOtherAddnLiftChargesDTO2);
        workEstimateOtherAddnLiftChargesDTO2.setId(2L);
        assertThat(workEstimateOtherAddnLiftChargesDTO1).isNotEqualTo(workEstimateOtherAddnLiftChargesDTO2);
        workEstimateOtherAddnLiftChargesDTO1.setId(null);
        assertThat(workEstimateOtherAddnLiftChargesDTO1).isNotEqualTo(workEstimateOtherAddnLiftChargesDTO2);
    }
}
