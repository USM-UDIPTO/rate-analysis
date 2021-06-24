package com.dxc.eproc.rateanalysis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkEstimateRoyaltyChargesMapperTest {

    private WorkEstimateRoyaltyChargesMapper workEstimateRoyaltyChargesMapper;

    @BeforeEach
    public void setUp() {
        workEstimateRoyaltyChargesMapper = new WorkEstimateRoyaltyChargesMapperImpl();
    }
}
