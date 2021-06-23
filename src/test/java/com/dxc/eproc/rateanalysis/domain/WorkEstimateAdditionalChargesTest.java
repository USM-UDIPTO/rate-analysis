package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateAdditionalChargesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateAdditionalCharges.class);
        WorkEstimateAdditionalCharges workEstimateAdditionalCharges1 = new WorkEstimateAdditionalCharges();
        workEstimateAdditionalCharges1.setId(1L);
        WorkEstimateAdditionalCharges workEstimateAdditionalCharges2 = new WorkEstimateAdditionalCharges();
        workEstimateAdditionalCharges2.setId(workEstimateAdditionalCharges1.getId());
        assertThat(workEstimateAdditionalCharges1).isEqualTo(workEstimateAdditionalCharges2);
        workEstimateAdditionalCharges2.setId(2L);
        assertThat(workEstimateAdditionalCharges1).isNotEqualTo(workEstimateAdditionalCharges2);
        workEstimateAdditionalCharges1.setId(null);
        assertThat(workEstimateAdditionalCharges1).isNotEqualTo(workEstimateAdditionalCharges2);
    }
}
