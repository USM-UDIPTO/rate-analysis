package com.dxc.eproc.rateanalysis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkEstimateLoadUnloadChargesMapperTest {

    private WorkEstimateLoadUnloadChargesMapper workEstimateLoadUnloadChargesMapper;

    @BeforeEach
    public void setUp() {
        workEstimateLoadUnloadChargesMapper = new WorkEstimateLoadUnloadChargesMapperImpl();
    }
}
