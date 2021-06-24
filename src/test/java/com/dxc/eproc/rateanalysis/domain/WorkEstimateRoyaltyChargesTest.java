package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateRoyaltyChargesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateRoyaltyCharges.class);
        WorkEstimateRoyaltyCharges workEstimateRoyaltyCharges1 = new WorkEstimateRoyaltyCharges();
        workEstimateRoyaltyCharges1.setId(1L);
        WorkEstimateRoyaltyCharges workEstimateRoyaltyCharges2 = new WorkEstimateRoyaltyCharges();
        workEstimateRoyaltyCharges2.setId(workEstimateRoyaltyCharges1.getId());
        assertThat(workEstimateRoyaltyCharges1).isEqualTo(workEstimateRoyaltyCharges2);
        workEstimateRoyaltyCharges2.setId(2L);
        assertThat(workEstimateRoyaltyCharges1).isNotEqualTo(workEstimateRoyaltyCharges2);
        workEstimateRoyaltyCharges1.setId(null);
        assertThat(workEstimateRoyaltyCharges1).isNotEqualTo(workEstimateRoyaltyCharges2);
    }
}
