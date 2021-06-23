package com.dxc.eproc.rateanalysis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkEstimateRoyaltyMapperTest {

    private WorkEstimateRoyaltyMapper workEstimateRoyaltyMapper;

    @BeforeEach
    public void setUp() {
        workEstimateRoyaltyMapper = new WorkEstimateRoyaltyMapperImpl();
    }
}
