package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.RaFormula;
import com.dxc.eproc.rateanalysis.repository.RaFormulaRepository;
import com.dxc.eproc.rateanalysis.service.RaFormulaService;
import com.dxc.eproc.rateanalysis.service.dto.RaFormulaDTO;
import com.dxc.eproc.rateanalysis.service.mapper.RaFormulaMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RaFormula}.
 */
@Service
@Transactional
public class RaFormulaServiceImpl implements RaFormulaService {

    private final Logger log = LoggerFactory.getLogger(RaFormulaServiceImpl.class);

    private final RaFormulaRepository raFormulaRepository;

    private final RaFormulaMapper raFormulaMapper;

    public RaFormulaServiceImpl(RaFormulaRepository raFormulaRepository, RaFormulaMapper raFormulaMapper) {
        this.raFormulaRepository = raFormulaRepository;
        this.raFormulaMapper = raFormulaMapper;
    }

    @Override
    public RaFormulaDTO save(RaFormulaDTO raFormulaDTO) {
        log.debug("Request to save RaFormula : {}", raFormulaDTO);
        RaFormula raFormula = raFormulaMapper.toEntity(raFormulaDTO);
        raFormula = raFormulaRepository.save(raFormula);
        return raFormulaMapper.toDto(raFormula);
    }

    @Override
    public Optional<RaFormulaDTO> partialUpdate(RaFormulaDTO raFormulaDTO) {
        log.debug("Request to partially update RaFormula : {}", raFormulaDTO);

        return raFormulaRepository
            .findById(raFormulaDTO.getId())
            .map(
                existingRaFormula -> {
                    raFormulaMapper.partialUpdate(existingRaFormula, raFormulaDTO);
                    return existingRaFormula;
                }
            )
            .map(raFormulaRepository::save)
            .map(raFormulaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RaFormulaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RaFormulas");
        return raFormulaRepository.findAll(pageable).map(raFormulaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RaFormulaDTO> findOne(Long id) {
        log.debug("Request to get RaFormula : {}", id);
        return raFormulaRepository.findById(id).map(raFormulaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RaFormula : {}", id);
        raFormulaRepository.deleteById(id);
    }
}
