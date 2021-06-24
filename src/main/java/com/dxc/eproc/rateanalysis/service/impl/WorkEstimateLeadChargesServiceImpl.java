package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateLeadCharges;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateLeadChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateLeadChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLeadChargesDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateLeadChargesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateLeadCharges}.
 */
@Service
@Transactional
public class WorkEstimateLeadChargesServiceImpl implements WorkEstimateLeadChargesService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateLeadChargesServiceImpl.class);

    private final WorkEstimateLeadChargesRepository workEstimateLeadChargesRepository;

    private final WorkEstimateLeadChargesMapper workEstimateLeadChargesMapper;

    public WorkEstimateLeadChargesServiceImpl(
        WorkEstimateLeadChargesRepository workEstimateLeadChargesRepository,
        WorkEstimateLeadChargesMapper workEstimateLeadChargesMapper
    ) {
        this.workEstimateLeadChargesRepository = workEstimateLeadChargesRepository;
        this.workEstimateLeadChargesMapper = workEstimateLeadChargesMapper;
    }

    @Override
    public WorkEstimateLeadChargesDTO save(WorkEstimateLeadChargesDTO workEstimateLeadChargesDTO) {
        log.debug("Request to save WorkEstimateLeadCharges : {}", workEstimateLeadChargesDTO);
        WorkEstimateLeadCharges workEstimateLeadCharges = workEstimateLeadChargesMapper.toEntity(workEstimateLeadChargesDTO);
        workEstimateLeadCharges = workEstimateLeadChargesRepository.save(workEstimateLeadCharges);
        return workEstimateLeadChargesMapper.toDto(workEstimateLeadCharges);
    }

    @Override
    public Optional<WorkEstimateLeadChargesDTO> partialUpdate(WorkEstimateLeadChargesDTO workEstimateLeadChargesDTO) {
        log.debug("Request to partially update WorkEstimateLeadCharges : {}", workEstimateLeadChargesDTO);

        return workEstimateLeadChargesRepository
            .findById(workEstimateLeadChargesDTO.getId())
            .map(
                existingWorkEstimateLeadCharges -> {
                    workEstimateLeadChargesMapper.partialUpdate(existingWorkEstimateLeadCharges, workEstimateLeadChargesDTO);
                    return existingWorkEstimateLeadCharges;
                }
            )
            .map(workEstimateLeadChargesRepository::save)
            .map(workEstimateLeadChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateLeadChargesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateLeadCharges");
        return workEstimateLeadChargesRepository.findAll(pageable).map(workEstimateLeadChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateLeadChargesDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateLeadCharges : {}", id);
        return workEstimateLeadChargesRepository.findById(id).map(workEstimateLeadChargesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateLeadCharges : {}", id);
        workEstimateLeadChargesRepository.deleteById(id);
    }
}
