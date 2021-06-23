package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateRoyaltyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateRoyalty.class);
        WorkEstimateRoyalty workEstimateRoyalty1 = new WorkEstimateRoyalty();
        workEstimateRoyalty1.setId(1L);
        WorkEstimateRoyalty workEstimateRoyalty2 = new WorkEstimateRoyalty();
        workEstimateRoyalty2.setId(workEstimateRoyalty1.getId());
        assertThat(workEstimateRoyalty1).isEqualTo(workEstimateRoyalty2);
        workEstimateRoyalty2.setId(2L);
        assertThat(workEstimateRoyalty1).isNotEqualTo(workEstimateRoyalty2);
        workEstimateRoyalty1.setId(null);
        assertThat(workEstimateRoyalty1).isNotEqualTo(workEstimateRoyalty2);
    }
}
