package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.RaParametersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RaParameters} and its DTO {@link RaParametersDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RaParametersMapper extends EntityMapper<RaParametersDTO, RaParameters> {}
