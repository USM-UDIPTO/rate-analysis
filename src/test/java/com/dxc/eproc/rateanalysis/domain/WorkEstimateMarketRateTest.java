package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateMarketRateTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateMarketRate.class);
        WorkEstimateMarketRate workEstimateMarketRate1 = new WorkEstimateMarketRate();
        workEstimateMarketRate1.setId(1L);
        WorkEstimateMarketRate workEstimateMarketRate2 = new WorkEstimateMarketRate();
        workEstimateMarketRate2.setId(workEstimateMarketRate1.getId());
        assertThat(workEstimateMarketRate1).isEqualTo(workEstimateMarketRate2);
        workEstimateMarketRate2.setId(2L);
        assertThat(workEstimateMarketRate1).isNotEqualTo(workEstimateMarketRate2);
        workEstimateMarketRate1.setId(null);
        assertThat(workEstimateMarketRate1).isNotEqualTo(workEstimateMarketRate2);
    }
}
