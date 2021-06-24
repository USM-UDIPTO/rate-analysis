package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.RaConfigurationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RaConfiguration} and its DTO {@link RaConfigurationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RaConfigurationMapper extends EntityMapper<RaConfigurationDTO, RaConfiguration> {}
