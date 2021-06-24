package com.dxc.eproc.rateanalysis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkEstimateRateAnalysisDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkEstimateRateAnalysisDTO.class);
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO1 = new WorkEstimateRateAnalysisDTO();
        workEstimateRateAnalysisDTO1.setId(1L);
        WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO2 = new WorkEstimateRateAnalysisDTO();
        assertThat(workEstimateRateAnalysisDTO1).isNotEqualTo(workEstimateRateAnalysisDTO2);
        workEstimateRateAnalysisDTO2.setId(workEstimateRateAnalysisDTO1.getId());
        assertThat(workEstimateRateAnalysisDTO1).isEqualTo(workEstimateRateAnalysisDTO2);
        workEstimateRateAnalysisDTO2.setId(2L);
        assertThat(workEstimateRateAnalysisDTO1).isNotEqualTo(workEstimateRateAnalysisDTO2);
        workEstimateRateAnalysisDTO1.setId(null);
        assertThat(workEstimateRateAnalysisDTO1).isNotEqualTo(workEstimateRateAnalysisDTO2);
    }
}
