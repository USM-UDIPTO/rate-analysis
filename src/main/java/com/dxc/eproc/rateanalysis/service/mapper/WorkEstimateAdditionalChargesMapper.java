package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateAdditionalChargesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateAdditionalCharges} and its DTO {@link WorkEstimateAdditionalChargesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateAdditionalChargesMapper
    extends EntityMapper<WorkEstimateAdditionalChargesDTO, WorkEstimateAdditionalCharges> {}
