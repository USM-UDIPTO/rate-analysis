package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateOtherAddnLiftChargesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateOtherAddnLiftCharges} and its DTO {@link WorkEstimateOtherAddnLiftChargesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateOtherAddnLiftChargesMapper
    extends EntityMapper<WorkEstimateOtherAddnLiftChargesDTO, WorkEstimateOtherAddnLiftCharges> {}
