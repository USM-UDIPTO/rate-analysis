package com.dxc.eproc.rateanalysis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.dxc.eproc.rateanalysis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RaParametersDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RaParametersDTO.class);
        RaParametersDTO raParametersDTO1 = new RaParametersDTO();
        raParametersDTO1.setId(1L);
        RaParametersDTO raParametersDTO2 = new RaParametersDTO();
        assertThat(raParametersDTO1).isNotEqualTo(raParametersDTO2);
        raParametersDTO2.setId(raParametersDTO1.getId());
        assertThat(raParametersDTO1).isEqualTo(raParametersDTO2);
        raParametersDTO2.setId(2L);
        assertThat(raParametersDTO1).isNotEqualTo(raParametersDTO2);
        raParametersDTO1.setId(null);
        assertThat(raParametersDTO1).isNotEqualTo(raParametersDTO2);
    }
}
