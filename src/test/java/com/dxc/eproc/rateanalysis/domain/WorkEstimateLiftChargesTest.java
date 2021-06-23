package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateLiftChargesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateLiftCharges.class);
        WorkEstimateLiftCharges workEstimateLiftCharges1 = new WorkEstimateLiftCharges();
        workEstimateLiftCharges1.setId(1L);
        WorkEstimateLiftCharges workEstimateLiftCharges2 = new WorkEstimateLiftCharges();
        workEstimateLiftCharges2.setId(workEstimateLiftCharges1.getId());
        assertThat(workEstimateLiftCharges1).isEqualTo(workEstimateLiftCharges2);
        workEstimateLiftCharges2.setId(2L);
        assertThat(workEstimateLiftCharges1).isNotEqualTo(workEstimateLiftCharges2);
        workEstimateLiftCharges1.setId(null);
        assertThat(workEstimateLiftCharges1).isNotEqualTo(workEstimateLiftCharges2);
    }
}
