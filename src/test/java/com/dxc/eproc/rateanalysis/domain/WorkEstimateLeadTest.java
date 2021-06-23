package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateLeadTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateLead.class);
        WorkEstimateLead workEstimateLead1 = new WorkEstimateLead();
        workEstimateLead1.setId(1L);
        WorkEstimateLead workEstimateLead2 = new WorkEstimateLead();
        workEstimateLead2.setId(workEstimateLead1.getId());
        assertThat(workEstimateLead1).isEqualTo(workEstimateLead2);
        workEstimateLead2.setId(2L);
        assertThat(workEstimateLead1).isNotEqualTo(workEstimateLead2);
        workEstimateLead1.setId(null);
        assertThat(workEstimateLead1).isNotEqualTo(workEstimateLead2);
    }
}
