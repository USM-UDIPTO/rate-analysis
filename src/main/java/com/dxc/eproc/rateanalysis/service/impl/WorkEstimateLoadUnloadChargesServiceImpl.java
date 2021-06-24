package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateLoadUnloadCharges;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateLoadUnloadChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateLoadUnloadChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateLoadUnloadChargesDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateLoadUnloadChargesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateLoadUnloadCharges}.
 */
@Service
@Transactional
public class WorkEstimateLoadUnloadChargesServiceImpl implements WorkEstimateLoadUnloadChargesService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateLoadUnloadChargesServiceImpl.class);

    private final WorkEstimateLoadUnloadChargesRepository workEstimateLoadUnloadChargesRepository;

    private final WorkEstimateLoadUnloadChargesMapper workEstimateLoadUnloadChargesMapper;

    public WorkEstimateLoadUnloadChargesServiceImpl(
        WorkEstimateLoadUnloadChargesRepository workEstimateLoadUnloadChargesRepository,
        WorkEstimateLoadUnloadChargesMapper workEstimateLoadUnloadChargesMapper
    ) {
        this.workEstimateLoadUnloadChargesRepository = workEstimateLoadUnloadChargesRepository;
        this.workEstimateLoadUnloadChargesMapper = workEstimateLoadUnloadChargesMapper;
    }

    @Override
    public WorkEstimateLoadUnloadChargesDTO save(WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO) {
        log.debug("Request to save WorkEstimateLoadUnloadCharges : {}", workEstimateLoadUnloadChargesDTO);
        WorkEstimateLoadUnloadCharges workEstimateLoadUnloadCharges = workEstimateLoadUnloadChargesMapper.toEntity(
            workEstimateLoadUnloadChargesDTO
        );
        workEstimateLoadUnloadCharges = workEstimateLoadUnloadChargesRepository.save(workEstimateLoadUnloadCharges);
        return workEstimateLoadUnloadChargesMapper.toDto(workEstimateLoadUnloadCharges);
    }

    @Override
    public Optional<WorkEstimateLoadUnloadChargesDTO> partialUpdate(WorkEstimateLoadUnloadChargesDTO workEstimateLoadUnloadChargesDTO) {
        log.debug("Request to partially update WorkEstimateLoadUnloadCharges : {}", workEstimateLoadUnloadChargesDTO);

        return workEstimateLoadUnloadChargesRepository
            .findById(workEstimateLoadUnloadChargesDTO.getId())
            .map(
                existingWorkEstimateLoadUnloadCharges -> {
                    workEstimateLoadUnloadChargesMapper.partialUpdate(
                        existingWorkEstimateLoadUnloadCharges,
                        workEstimateLoadUnloadChargesDTO
                    );
                    return existingWorkEstimateLoadUnloadCharges;
                }
            )
            .map(workEstimateLoadUnloadChargesRepository::save)
            .map(workEstimateLoadUnloadChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateLoadUnloadChargesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateLoadUnloadCharges");
        return workEstimateLoadUnloadChargesRepository.findAll(pageable).map(workEstimateLoadUnloadChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateLoadUnloadChargesDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateLoadUnloadCharges : {}", id);
        return workEstimateLoadUnloadChargesRepository.findById(id).map(workEstimateLoadUnloadChargesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateLoadUnloadCharges : {}", id);
        workEstimateLoadUnloadChargesRepository.deleteById(id);
    }
}
