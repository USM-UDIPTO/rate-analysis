package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateMarketRateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateMarketRate} and its DTO {@link WorkEstimateMarketRateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateMarketRateMapper extends EntityMapper<WorkEstimateMarketRateDTO, WorkEstimateMarketRate> {}
