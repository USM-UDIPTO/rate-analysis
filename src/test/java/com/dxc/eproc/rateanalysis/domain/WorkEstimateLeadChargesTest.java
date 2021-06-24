package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateLeadChargesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateLeadCharges.class);
        WorkEstimateLeadCharges workEstimateLeadCharges1 = new WorkEstimateLeadCharges();
        workEstimateLeadCharges1.setId(1L);
        WorkEstimateLeadCharges workEstimateLeadCharges2 = new WorkEstimateLeadCharges();
        workEstimateLeadCharges2.setId(workEstimateLeadCharges1.getId());
        assertThat(workEstimateLeadCharges1).isEqualTo(workEstimateLeadCharges2);
        workEstimateLeadCharges2.setId(2L);
        assertThat(workEstimateLeadCharges1).isNotEqualTo(workEstimateLeadCharges2);
        workEstimateLeadCharges1.setId(null);
        assertThat(workEstimateLeadCharges1).isNotEqualTo(workEstimateLeadCharges2);
    }
}
