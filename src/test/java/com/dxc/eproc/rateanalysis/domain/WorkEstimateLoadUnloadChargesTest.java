package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateLoadUnloadChargesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateLoadUnloadCharges.class);
        WorkEstimateLoadUnloadCharges workEstimateLoadUnloadCharges1 = new WorkEstimateLoadUnloadCharges();
        workEstimateLoadUnloadCharges1.setId(1L);
        WorkEstimateLoadUnloadCharges workEstimateLoadUnloadCharges2 = new WorkEstimateLoadUnloadCharges();
        workEstimateLoadUnloadCharges2.setId(workEstimateLoadUnloadCharges1.getId());
        assertThat(workEstimateLoadUnloadCharges1).isEqualTo(workEstimateLoadUnloadCharges2);
        workEstimateLoadUnloadCharges2.setId(2L);
        assertThat(workEstimateLoadUnloadCharges1).isNotEqualTo(workEstimateLoadUnloadCharges2);
        workEstimateLoadUnloadCharges1.setId(null);
        assertThat(workEstimateLoadUnloadCharges1).isNotEqualTo(workEstimateLoadUnloadCharges2);
    }
}
