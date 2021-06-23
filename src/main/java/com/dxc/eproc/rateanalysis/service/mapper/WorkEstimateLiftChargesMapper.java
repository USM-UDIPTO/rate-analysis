package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLiftChargesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateLiftCharges} and its DTO {@link WorkEstimateLiftChargesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateLiftChargesMapper extends EntityMapper<WorkEstimateLiftChargesDTO, WorkEstimateLiftCharges> {}
