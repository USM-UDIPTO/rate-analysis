package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRateAnalysisDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateRateAnalysis} and its DTO {@link WorkEstimateRateAnalysisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateRateAnalysisMapper extends EntityMapper<WorkEstimateRateAnalysisDTO, WorkEstimateRateAnalysis> {}
