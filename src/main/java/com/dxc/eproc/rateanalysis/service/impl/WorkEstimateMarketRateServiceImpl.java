package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateMarketRate;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateMarketRateRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateMarketRateService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateMarketRateDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateMarketRateMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateMarketRate}.
 */
@Service
@Transactional
public class WorkEstimateMarketRateServiceImpl implements WorkEstimateMarketRateService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateMarketRateServiceImpl.class);

    private final WorkEstimateMarketRateRepository workEstimateMarketRateRepository;

    private final WorkEstimateMarketRateMapper workEstimateMarketRateMapper;

    public WorkEstimateMarketRateServiceImpl(
        WorkEstimateMarketRateRepository workEstimateMarketRateRepository,
        WorkEstimateMarketRateMapper workEstimateMarketRateMapper
    ) {
        this.workEstimateMarketRateRepository = workEstimateMarketRateRepository;
        this.workEstimateMarketRateMapper = workEstimateMarketRateMapper;
    }

    @Override
    public WorkEstimateMarketRateDTO save(WorkEstimateMarketRateDTO workEstimateMarketRateDTO) {
        log.debug("Request to save WorkEstimateMarketRate : {}", workEstimateMarketRateDTO);
        WorkEstimateMarketRate workEstimateMarketRate = workEstimateMarketRateMapper.toEntity(workEstimateMarketRateDTO);
        workEstimateMarketRate = workEstimateMarketRateRepository.save(workEstimateMarketRate);
        return workEstimateMarketRateMapper.toDto(workEstimateMarketRate);
    }

    @Override
    public Optional<WorkEstimateMarketRateDTO> partialUpdate(WorkEstimateMarketRateDTO workEstimateMarketRateDTO) {
        log.debug("Request to partially update WorkEstimateMarketRate : {}", workEstimateMarketRateDTO);

        return workEstimateMarketRateRepository
            .findById(workEstimateMarketRateDTO.getId())
            .map(
                existingWorkEstimateMarketRate -> {
                    workEstimateMarketRateMapper.partialUpdate(existingWorkEstimateMarketRate, workEstimateMarketRateDTO);
                    return existingWorkEstimateMarketRate;
                }
            )
            .map(workEstimateMarketRateRepository::save)
            .map(workEstimateMarketRateMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateMarketRateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateMarketRates");
        return workEstimateMarketRateRepository.findAll(pageable).map(workEstimateMarketRateMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateMarketRateDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateMarketRate : {}", id);
        return workEstimateMarketRateRepository.findById(id).map(workEstimateMarketRateMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateMarketRate : {}", id);
        workEstimateMarketRateRepository.deleteById(id);
    }
}
