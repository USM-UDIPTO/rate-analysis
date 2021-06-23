package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RaParametersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RaParameters.class);
        RaParameters raParameters1 = new RaParameters();
        raParameters1.setId(1L);
        RaParameters raParameters2 = new RaParameters();
        raParameters2.setId(raParameters1.getId());
        assertThat(raParameters1).isEqualTo(raParameters2);
        raParameters2.setId(2L);
        assertThat(raParameters1).isNotEqualTo(raParameters2);
        raParameters1.setId(null);
        assertThat(raParameters1).isNotEqualTo(raParameters2);
    }
}
