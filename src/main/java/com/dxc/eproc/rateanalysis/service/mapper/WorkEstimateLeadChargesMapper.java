package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLeadChargesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateLeadCharges} and its DTO {@link WorkEstimateLeadChargesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateLeadChargesMapper extends EntityMapper<WorkEstimateLeadChargesDTO, WorkEstimateLeadCharges> {}
