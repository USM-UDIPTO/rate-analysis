package com.dxc.eproc.rateanalysis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateLeadDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateLeadDTO.class);
        WorkEstimateLeadDTO workEstimateLeadDTO1 = new WorkEstimateLeadDTO();
        workEstimateLeadDTO1.setId(1L);
        WorkEstimateLeadDTO workEstimateLeadDTO2 = new WorkEstimateLeadDTO();
        assertThat(workEstimateLeadDTO1).isNotEqualTo(workEstimateLeadDTO2);
        workEstimateLeadDTO2.setId(workEstimateLeadDTO1.getId());
        assertThat(workEstimateLeadDTO1).isEqualTo(workEstimateLeadDTO2);
        workEstimateLeadDTO2.setId(2L);
        assertThat(workEstimateLeadDTO1).isNotEqualTo(workEstimateLeadDTO2);
        workEstimateLeadDTO1.setId(null);
        assertThat(workEstimateLeadDTO1).isNotEqualTo(workEstimateLeadDTO2);
    }
}
