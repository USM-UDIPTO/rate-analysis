package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRoyaltyChargesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkEstimateRoyaltyCharges} and its DTO {@link WorkEstimateRoyaltyChargesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkEstimateRoyaltyChargesMapper extends EntityMapper<WorkEstimateRoyaltyChargesDTO, WorkEstimateRoyaltyCharges> {}
