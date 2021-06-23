package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLeadDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateLead} and its DTO {@link WorkEstimateLeadDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateLeadMapper extends EntityMapper<WorkEstimateLeadDTO, WorkEstimateLead> {}
