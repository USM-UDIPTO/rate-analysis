package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLoadUnloadChargesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateLoadUnloadCharges} and its DTO {@link WorkEstimateLoadUnloadChargesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateLoadUnloadChargesMapper
    extends EntityMapper<WorkEstimateLoadUnloadChargesDTO, WorkEstimateLoadUnloadCharges> {}
