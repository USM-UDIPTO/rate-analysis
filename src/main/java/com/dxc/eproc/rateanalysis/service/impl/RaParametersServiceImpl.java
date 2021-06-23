package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.RaParameters;
import com.dxc.eproc.rateanalysis.repository.RaParametersRepository;
import com.dxc.eproc.rateanalysis.service.RaParametersService;
import com.dxc.eproc.rateanalysis.service.dto.RaParametersDTO;
import com.dxc.eproc.rateanalysis.service.mapper.RaParametersMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RaParameters}.
 */
@Service
@Transactional
public class RaParametersServiceImpl implements RaParametersService {

    private final Logger log = LoggerFactory.getLogger(RaParametersServiceImpl.class);

    private final RaParametersRepository raParametersRepository;

    private final RaParametersMapper raParametersMapper;

    public RaParametersServiceImpl(RaParametersRepository raParametersRepository, RaParametersMapper raParametersMapper) {
        this.raParametersRepository = raParametersRepository;
        this.raParametersMapper = raParametersMapper;
    }

    @Override
    public RaParametersDTO save(RaParametersDTO raParametersDTO) {
        log.debug("Request to save RaParameters : {}", raParametersDTO);
        RaParameters raParameters = raParametersMapper.toEntity(raParametersDTO);
        raParameters = raParametersRepository.save(raParameters);
        return raParametersMapper.toDto(raParameters);
    }

    @Override
    public Optional<RaParametersDTO> partialUpdate(RaParametersDTO raParametersDTO) {
        log.debug("Request to partially update RaParameters : {}", raParametersDTO);

        return raParametersRepository
            .findById(raParametersDTO.getId())
            .map(
                existingRaParameters -> {
                    raParametersMapper.partialUpdate(existingRaParameters, raParametersDTO);
                    return existingRaParameters;
                }
            )
            .map(raParametersRepository::save)
            .map(raParametersMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RaParametersDTO> findAll() {
        log.debug("Request to get all RaParameters");
        return raParametersRepository.findAll().stream().map(raParametersMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RaParametersDTO> findOne(Long id) {
        log.debug("Request to get RaParameters : {}", id);
        return raParametersRepository.findById(id).map(raParametersMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RaParameters : {}", id);
        raParametersRepository.deleteById(id);
    }
}
