package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateRateAnalysisTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateRateAnalysis.class);
        WorkEstimateRateAnalysis workEstimateRateAnalysis1 = new WorkEstimateRateAnalysis();
        workEstimateRateAnalysis1.setId(1L);
        WorkEstimateRateAnalysis workEstimateRateAnalysis2 = new WorkEstimateRateAnalysis();
        workEstimateRateAnalysis2.setId(workEstimateRateAnalysis1.getId());
        assertThat(workEstimateRateAnalysis1).isEqualTo(workEstimateRateAnalysis2);
        workEstimateRateAnalysis2.setId(2L);
        assertThat(workEstimateRateAnalysis1).isNotEqualTo(workEstimateRateAnalysis2);
        workEstimateRateAnalysis1.setId(null);
        assertThat(workEstimateRateAnalysis1).isNotEqualTo(workEstimateRateAnalysis2);
    }
}
