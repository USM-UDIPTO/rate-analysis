package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateAdditionalCharges;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateAdditionalChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateAdditionalChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateAdditionalChargesDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateAdditionalChargesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateAdditionalCharges}.
 */
@Service
@Transactional
public class WorkEstimateAdditionalChargesServiceImpl implements WorkEstimateAdditionalChargesService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateAdditionalChargesServiceImpl.class);

    private final WorkEstimateAdditionalChargesRepository workEstimateAdditionalChargesRepository;

    private final WorkEstimateAdditionalChargesMapper workEstimateAdditionalChargesMapper;

    public WorkEstimateAdditionalChargesServiceImpl(
        WorkEstimateAdditionalChargesRepository workEstimateAdditionalChargesRepository,
        WorkEstimateAdditionalChargesMapper workEstimateAdditionalChargesMapper
    ) {
        this.workEstimateAdditionalChargesRepository = workEstimateAdditionalChargesRepository;
        this.workEstimateAdditionalChargesMapper = workEstimateAdditionalChargesMapper;
    }

    @Override
    public WorkEstimateAdditionalChargesDTO save(WorkEstimateAdditionalChargesDTO workEstimateAdditionalChargesDTO) {
        log.debug("Request to save WorkEstimateAdditionalCharges : {}", workEstimateAdditionalChargesDTO);
        WorkEstimateAdditionalCharges workEstimateAdditionalCharges = workEstimateAdditionalChargesMapper.toEntity(
            workEstimateAdditionalChargesDTO
        );
        workEstimateAdditionalCharges = workEstimateAdditionalChargesRepository.save(workEstimateAdditionalCharges);
        return workEstimateAdditionalChargesMapper.toDto(workEstimateAdditionalCharges);
    }

    @Override
    public Optional<WorkEstimateAdditionalChargesDTO> partialUpdate(WorkEstimateAdditionalChargesDTO workEstimateAdditionalChargesDTO) {
        log.debug("Request to partially update WorkEstimateAdditionalCharges : {}", workEstimateAdditionalChargesDTO);

        return workEstimateAdditionalChargesRepository
            .findById(workEstimateAdditionalChargesDTO.getId())
            .map(
                existingWorkEstimateAdditionalCharges -> {
                    workEstimateAdditionalChargesMapper.partialUpdate(
                        existingWorkEstimateAdditionalCharges,
                        workEstimateAdditionalChargesDTO
                    );
                    return existingWorkEstimateAdditionalCharges;
                }
            )
            .map(workEstimateAdditionalChargesRepository::save)
            .map(workEstimateAdditionalChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateAdditionalChargesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateAdditionalCharges");
        return workEstimateAdditionalChargesRepository.findAll(pageable).map(workEstimateAdditionalChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateAdditionalChargesDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateAdditionalCharges : {}", id);
        return workEstimateAdditionalChargesRepository.findById(id).map(workEstimateAdditionalChargesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateAdditionalCharges : {}", id);
        workEstimateAdditionalChargesRepository.deleteById(id);
    }
}
