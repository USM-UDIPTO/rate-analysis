package com.dxc.eproc.rateanalysis.service.mapper;

import com.dxc.eproc.rateanalysis.domain.*;
import com.dxc.eproc.rateanalysis.service.dto.RaFormulaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RaFormula} and its DTO {@link RaFormulaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RaFormulaMapper extends EntityMapper<RaFormulaDTO, RaFormula> {}
