package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRoyaltyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateRoyalty} and its DTO {@link WorkEstimateRoyaltyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateRoyaltyMapper extends EntityMapper<WorkEstimateRoyaltyDTO, WorkEstimateRoyalty> {}
