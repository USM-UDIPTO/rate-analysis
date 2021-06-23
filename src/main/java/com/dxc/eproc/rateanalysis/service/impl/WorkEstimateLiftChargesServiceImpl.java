package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateLiftCharges;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateLiftChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateLiftChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLiftChargesDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateLiftChargesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateLiftCharges}.
 */
@Service
@Transactional
public class WorkEstimateLiftChargesServiceImpl implements WorkEstimateLiftChargesService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateLiftChargesServiceImpl.class);

    private final WorkEstimateLiftChargesRepository workEstimateLiftChargesRepository;

    private final WorkEstimateLiftChargesMapper workEstimateLiftChargesMapper;

    public WorkEstimateLiftChargesServiceImpl(
        WorkEstimateLiftChargesRepository workEstimateLiftChargesRepository,
        WorkEstimateLiftChargesMapper workEstimateLiftChargesMapper
    ) {
        this.workEstimateLiftChargesRepository = workEstimateLiftChargesRepository;
        this.workEstimateLiftChargesMapper = workEstimateLiftChargesMapper;
    }

    @Override
    public WorkEstimateLiftChargesDTO save(WorkEstimateLiftChargesDTO workEstimateLiftChargesDTO) {
        log.debug("Request to save WorkEstimateLiftCharges : {}", workEstimateLiftChargesDTO);
        WorkEstimateLiftCharges workEstimateLiftCharges = workEstimateLiftChargesMapper.toEntity(workEstimateLiftChargesDTO);
        workEstimateLiftCharges = workEstimateLiftChargesRepository.save(workEstimateLiftCharges);
        return workEstimateLiftChargesMapper.toDto(workEstimateLiftCharges);
    }

    @Override
    public Optional<WorkEstimateLiftChargesDTO> partialUpdate(WorkEstimateLiftChargesDTO workEstimateLiftChargesDTO) {
        log.debug("Request to partially update WorkEstimateLiftCharges : {}", workEstimateLiftChargesDTO);

        return workEstimateLiftChargesRepository
            .findById(workEstimateLiftChargesDTO.getId())
            .map(
                existingWorkEstimateLiftCharges -> {
                    workEstimateLiftChargesMapper.partialUpdate(existingWorkEstimateLiftCharges, workEstimateLiftChargesDTO);
                    return existingWorkEstimateLiftCharges;
                }
            )
            .map(workEstimateLiftChargesRepository::save)
            .map(workEstimateLiftChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateLiftChargesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateLiftCharges");
        return workEstimateLiftChargesRepository.findAll(pageable).map(workEstimateLiftChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateLiftChargesDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateLiftCharges : {}", id);
        return workEstimateLiftChargesRepository.findById(id).map(workEstimateLiftChargesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateLiftCharges : {}", id);
        workEstimateLiftChargesRepository.deleteById(id);
    }
}
