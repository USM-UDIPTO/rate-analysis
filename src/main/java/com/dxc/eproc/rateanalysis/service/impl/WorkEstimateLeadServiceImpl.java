package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateLead;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateLeadRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateLeadService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLeadDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateLeadMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateLead}.
 */
@Service
@Transactional
public class WorkEstimateLeadServiceImpl implements WorkEstimateLeadService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateLeadServiceImpl.class);

    private final WorkEstimateLeadRepository workEstimateLeadRepository;

    private final WorkEstimateLeadMapper workEstimateLeadMapper;

    public WorkEstimateLeadServiceImpl(
        WorkEstimateLeadRepository workEstimateLeadRepository,
        WorkEstimateLeadMapper workEstimateLeadMapper
    ) {
        this.workEstimateLeadRepository = workEstimateLeadRepository;
        this.workEstimateLeadMapper = workEstimateLeadMapper;
    }

    @Override
    public WorkEstimateLeadDTO save(WorkEstimateLeadDTO workEstimateLeadDTO) {
        log.debug("Request to save WorkEstimateLead : {}", workEstimateLeadDTO);
        WorkEstimateLead workEstimateLead = workEstimateLeadMapper.toEntity(workEstimateLeadDTO);
        workEstimateLead = workEstimateLeadRepository.save(workEstimateLead);
        return workEstimateLeadMapper.toDto(workEstimateLead);
    }

    @Override
    public Optional<WorkEstimateLeadDTO> partialUpdate(WorkEstimateLeadDTO workEstimateLeadDTO) {
        log.debug("Request to partially update WorkEstimateLead : {}", workEstimateLeadDTO);

        return workEstimateLeadRepository
            .findById(workEstimateLeadDTO.getId())
            .map(
                existingWorkEstimateLead -> {
                    workEstimateLeadMapper.partialUpdate(existingWorkEstimateLead, workEstimateLeadDTO);
                    return existingWorkEstimateLead;
                }
            )
            .map(workEstimateLeadRepository::save)
            .map(workEstimateLeadMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateLeadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateLeads");
        return workEstimateLeadRepository.findAll(pageable).map(workEstimateLeadMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateLeadDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateLead : {}", id);
        return workEstimateLeadRepository.findById(id).map(workEstimateLeadMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateLead : {}", id);
        workEstimateLeadRepository.deleteById(id);
    }
}
