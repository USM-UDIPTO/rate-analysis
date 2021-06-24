package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateRateAnalysis;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateRateAnalysisRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateRateAnalysisService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRateAnalysisDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateRateAnalysisMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateRateAnalysis}.
 */
@Service
@Transactional
public class WorkEstimateRateAnalysisServiceImpl implements WorkEstimateRateAnalysisService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateRateAnalysisServiceImpl.class);

    private final WorkEstimateRateAnalysisRepository workEstimateRateAnalysisRepository;

    private final WorkEstimateRateAnalysisMapper workEstimateRateAnalysisMapper;

    public WorkEstimateRateAnalysisServiceImpl(
        WorkEstimateRateAnalysisRepository workEstimateRateAnalysisRepository,
        WorkEstimateRateAnalysisMapper workEstimateRateAnalysisMapper
    ) {
        this.workEstimateRateAnalysisRepository = workEstimateRateAnalysisRepository;
        this.workEstimateRateAnalysisMapper = workEstimateRateAnalysisMapper;
    }

    @Override
    public WorkEstimateRateAnalysisDTO save(WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO) {
        log.debug("Request to save WorkEstimateRateAnalysis : {}", workEstimateRateAnalysisDTO);
        WorkEstimateRateAnalysis workEstimateRateAnalysis = workEstimateRateAnalysisMapper.toEntity(workEstimateRateAnalysisDTO);
        workEstimateRateAnalysis = workEstimateRateAnalysisRepository.save(workEstimateRateAnalysis);
        return workEstimateRateAnalysisMapper.toDto(workEstimateRateAnalysis);
    }

    @Override
    public Optional<WorkEstimateRateAnalysisDTO> partialUpdate(WorkEstimateRateAnalysisDTO workEstimateRateAnalysisDTO) {
        log.debug("Request to partially update WorkEstimateRateAnalysis : {}", workEstimateRateAnalysisDTO);

        return workEstimateRateAnalysisRepository
            .findById(workEstimateRateAnalysisDTO.getId())
            .map(
                existingWorkEstimateRateAnalysis -> {
                    workEstimateRateAnalysisMapper.partialUpdate(existingWorkEstimateRateAnalysis, workEstimateRateAnalysisDTO);
                    return existingWorkEstimateRateAnalysis;
                }
            )
            .map(workEstimateRateAnalysisRepository::save)
            .map(workEstimateRateAnalysisMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateRateAnalysisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateRateAnalyses");
        return workEstimateRateAnalysisRepository.findAll(pageable).map(workEstimateRateAnalysisMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateRateAnalysisDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateRateAnalysis : {}", id);
        return workEstimateRateAnalysisRepository.findById(id).map(workEstimateRateAnalysisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateRateAnalysis : {}", id);
        workEstimateRateAnalysisRepository.deleteById(id);
    }
}
