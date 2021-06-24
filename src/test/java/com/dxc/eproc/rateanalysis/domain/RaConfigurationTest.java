package com.dxc.eproc.rateanalysis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RaConfigurationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RaConfiguration.class);
        RaConfiguration raConfiguration1 = new RaConfiguration();
        raConfiguration1.setId(1L);
        RaConfiguration raConfiguration2 = new RaConfiguration();
        raConfiguration2.setId(raConfiguration1.getId());
        assertThat(raConfiguration1).isEqualTo(raConfiguration2);
        raConfiguration2.setId(2L);
        assertThat(raConfiguration1).isNotEqualTo(raConfiguration2);
        raConfiguration1.setId(null);
        assertThat(raConfiguration1).isNotEqualTo(raConfiguration2);
    }
}
