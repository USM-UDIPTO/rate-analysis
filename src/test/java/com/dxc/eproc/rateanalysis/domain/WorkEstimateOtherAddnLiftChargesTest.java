package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateOtherAddnLiftChargesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateOtherAddnLiftCharges.class);
        WorkEstimateOtherAddnLiftCharges workEstimateOtherAddnLiftCharges1 = new WorkEstimateOtherAddnLiftCharges();
        workEstimateOtherAddnLiftCharges1.setId(1L);
        WorkEstimateOtherAddnLiftCharges workEstimateOtherAddnLiftCharges2 = new WorkEstimateOtherAddnLiftCharges();
        workEstimateOtherAddnLiftCharges2.setId(workEstimateOtherAddnLiftCharges1.getId());
        assertThat(workEstimateOtherAddnLiftCharges1).isEqualTo(workEstimateOtherAddnLiftCharges2);
        workEstimateOtherAddnLiftCharges2.setId(2L);
        assertThat(workEstimateOtherAddnLiftCharges1).isNotEqualTo(workEstimateOtherAddnLiftCharges2);
        workEstimateOtherAddnLiftCharges1.setId(null);
        assertThat(workEstimateOtherAddnLiftCharges1).isNotEqualTo(workEstimateOtherAddnLiftCharges2);
    }
}
