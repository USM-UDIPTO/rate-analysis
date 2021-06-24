package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyaltyCharges;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateRoyaltyChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateRoyaltyChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRoyaltyChargesDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateRoyaltyChargesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateRoyaltyCharges}.
 */
@Service
@Transactional
public class WorkEstimateRoyaltyChargesServiceImpl implements WorkEstimateRoyaltyChargesService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateRoyaltyChargesServiceImpl.class);

    private final WorkEstimateRoyaltyChargesRepository workEstimateRoyaltyChargesRepository;

    private final WorkEstimateRoyaltyChargesMapper workEstimateRoyaltyChargesMapper;

    public WorkEstimateRoyaltyChargesServiceImpl(
        WorkEstimateRoyaltyChargesRepository workEstimateRoyaltyChargesRepository,
        WorkEstimateRoyaltyChargesMapper workEstimateRoyaltyChargesMapper
    ) {
        this.workEstimateRoyaltyChargesRepository = workEstimateRoyaltyChargesRepository;
        this.workEstimateRoyaltyChargesMapper = workEstimateRoyaltyChargesMapper;
    }

    @Override
    public WorkEstimateRoyaltyChargesDTO save(WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO) {
        log.debug("Request to save WorkEstimateRoyaltyCharges : {}", workEstimateRoyaltyChargesDTO);
        WorkEstimateRoyaltyCharges workEstimateRoyaltyCharges = workEstimateRoyaltyChargesMapper.toEntity(workEstimateRoyaltyChargesDTO);
        workEstimateRoyaltyCharges = workEstimateRoyaltyChargesRepository.save(workEstimateRoyaltyCharges);
        return workEstimateRoyaltyChargesMapper.toDto(workEstimateRoyaltyCharges);
    }

    @Override
    public Optional<WorkEstimateRoyaltyChargesDTO> partialUpdate(WorkEstimateRoyaltyChargesDTO workEstimateRoyaltyChargesDTO) {
        log.debug("Request to partially update WorkEstimateRoyaltyCharges : {}", workEstimateRoyaltyChargesDTO);

        return workEstimateRoyaltyChargesRepository
            .findById(workEstimateRoyaltyChargesDTO.getId())
            .map(
                existingWorkEstimateRoyaltyCharges -> {
                    workEstimateRoyaltyChargesMapper.partialUpdate(existingWorkEstimateRoyaltyCharges, workEstimateRoyaltyChargesDTO);
                    return existingWorkEstimateRoyaltyCharges;
                }
            )
            .map(workEstimateRoyaltyChargesRepository::save)
            .map(workEstimateRoyaltyChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateRoyaltyChargesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateRoyaltyCharges");
        return workEstimateRoyaltyChargesRepository.findAll(pageable).map(workEstimateRoyaltyChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateRoyaltyChargesDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateRoyaltyCharges : {}", id);
        return workEstimateRoyaltyChargesRepository.findById(id).map(workEstimateRoyaltyChargesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateRoyaltyCharges : {}", id);
        workEstimateRoyaltyChargesRepository.deleteById(id);
    }
}
