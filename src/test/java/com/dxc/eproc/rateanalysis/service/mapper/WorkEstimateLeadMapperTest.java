package com.dxc.eproc.rateanalysis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkEstimateLeadMapperTest {

    private WorkEstimateLeadMapper workEstimateLeadMapper;

    @BeforeEach
    public void setUp() {
        workEstimateLeadMapper = new WorkEstimateLeadMapperImpl();
    }
}
