package com.dxc.eproc.rateanalysis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RaParametersMapperTest {

    private RaParametersMapper raParametersMapper;

    @BeforeEach
    public void setUp() {
        raParametersMapper = new RaParametersMapperImpl();
    }
}
