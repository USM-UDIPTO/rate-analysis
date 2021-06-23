package com.dxc.eproc.rateanalysis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateRoyaltyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateRoyaltyDTO.class);
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO1 = new WorkEstimateRoyaltyDTO();
        workEstimateRoyaltyDTO1.setId(1L);
        WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO2 = new WorkEstimateRoyaltyDTO();
        assertThat(workEstimateRoyaltyDTO1).isNotEqualTo(workEstimateRoyaltyDTO2);
        workEstimateRoyaltyDTO2.setId(workEstimateRoyaltyDTO1.getId());
        assertThat(workEstimateRoyaltyDTO1).isEqualTo(workEstimateRoyaltyDTO2);
        workEstimateRoyaltyDTO2.setId(2L);
        assertThat(workEstimateRoyaltyDTO1).isNotEqualTo(workEstimateRoyaltyDTO2);
        workEstimateRoyaltyDTO1.setId(null);
        assertThat(workEstimateRoyaltyDTO1).isNotEqualTo(workEstimateRoyaltyDTO2);
    }
}
