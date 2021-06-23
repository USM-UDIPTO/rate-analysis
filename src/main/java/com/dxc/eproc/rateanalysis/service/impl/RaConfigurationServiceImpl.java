package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.RaConfiguration;
import com.dxc.eproc.rateanalysis.repository.RaConfigurationRepository;
import com.dxc.eproc.rateanalysis.service.RaConfigurationService;
import com.dxc.eproc.rateanalysis.service.dto.RaConfigurationDTO;
import com.dxc.eproc.rateanalysis.service.mapper.RaConfigurationMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RaConfiguration}.
 */
@Service
@Transactional
public class RaConfigurationServiceImpl implements RaConfigurationService {

    private final Logger log = LoggerFactory.getLogger(RaConfigurationServiceImpl.class);

    private final RaConfigurationRepository raConfigurationRepository;

    private final RaConfigurationMapper raConfigurationMapper;

    public RaConfigurationServiceImpl(RaConfigurationRepository raConfigurationRepository, RaConfigurationMapper raConfigurationMapper) {
        this.raConfigurationRepository = raConfigurationRepository;
        this.raConfigurationMapper = raConfigurationMapper;
    }

    @Override
    public RaConfigurationDTO save(RaConfigurationDTO raConfigurationDTO) {
        log.debug("Request to save RaConfiguration : {}", raConfigurationDTO);
        RaConfiguration raConfiguration = raConfigurationMapper.toEntity(raConfigurationDTO);
        raConfiguration = raConfigurationRepository.save(raConfiguration);
        return raConfigurationMapper.toDto(raConfiguration);
    }

    @Override
    public Optional<RaConfigurationDTO> partialUpdate(RaConfigurationDTO raConfigurationDTO) {
        log.debug("Request to partially update RaConfiguration : {}", raConfigurationDTO);

        return raConfigurationRepository
            .findById(raConfigurationDTO.getId())
            .map(
                existingRaConfiguration -> {
                    raConfigurationMapper.partialUpdate(existingRaConfiguration, raConfigurationDTO);
                    return existingRaConfiguration;
                }
            )
            .map(raConfigurationRepository::save)
            .map(raConfigurationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RaConfigurationDTO> findAll() {
        log.debug("Request to get all RaConfigurations");
        return raConfigurationRepository
            .findAll()
            .stream()
            .map(raConfigurationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RaConfigurationDTO> findOne(Long id) {
        log.debug("Request to get RaConfiguration : {}", id);
        return raConfigurationRepository.findById(id).map(raConfigurationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RaConfiguration : {}", id);
        raConfigurationRepository.deleteById(id);
    }
}
