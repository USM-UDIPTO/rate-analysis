package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateOtherAddnLiftCharges;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateOtherAddnLiftChargesRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateOtherAddnLiftChargesService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateOtherAddnLiftChargesDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateOtherAddnLiftChargesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateOtherAddnLiftCharges}.
 */
@Service
@Transactional
public class WorkEstimateOtherAddnLiftChargesServiceImpl implements WorkEstimateOtherAddnLiftChargesService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateOtherAddnLiftChargesServiceImpl.class);

    private final WorkEstimateOtherAddnLiftChargesRepository workEstimateOtherAddnLiftChargesRepository;

    private final WorkEstimateOtherAddnLiftChargesMapper workEstimateOtherAddnLiftChargesMapper;

    public WorkEstimateOtherAddnLiftChargesServiceImpl(
        WorkEstimateOtherAddnLiftChargesRepository workEstimateOtherAddnLiftChargesRepository,
        WorkEstimateOtherAddnLiftChargesMapper workEstimateOtherAddnLiftChargesMapper
    ) {
        this.workEstimateOtherAddnLiftChargesRepository = workEstimateOtherAddnLiftChargesRepository;
        this.workEstimateOtherAddnLiftChargesMapper = workEstimateOtherAddnLiftChargesMapper;
    }

    @Override
    public WorkEstimateOtherAddnLiftChargesDTO save(WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO) {
        log.debug("Request to save WorkEstimateOtherAddnLiftCharges : {}", workEstimateOtherAddnLiftChargesDTO);
        WorkEstimateOtherAddnLiftCharges workEstimateOtherAddnLiftCharges = workEstimateOtherAddnLiftChargesMapper.toEntity(
            workEstimateOtherAddnLiftChargesDTO
        );
        workEstimateOtherAddnLiftCharges = workEstimateOtherAddnLiftChargesRepository.save(workEstimateOtherAddnLiftCharges);
        return workEstimateOtherAddnLiftChargesMapper.toDto(workEstimateOtherAddnLiftCharges);
    }

    @Override
    public Optional<WorkEstimateOtherAddnLiftChargesDTO> partialUpdate(
        WorkEstimateOtherAddnLiftChargesDTO workEstimateOtherAddnLiftChargesDTO
    ) {
        log.debug("Request to partially update WorkEstimateOtherAddnLiftCharges : {}", workEstimateOtherAddnLiftChargesDTO);

        return workEstimateOtherAddnLiftChargesRepository
            .findById(workEstimateOtherAddnLiftChargesDTO.getId())
            .map(
                existingWorkEstimateOtherAddnLiftCharges -> {
                    workEstimateOtherAddnLiftChargesMapper.partialUpdate(
                        existingWorkEstimateOtherAddnLiftCharges,
                        workEstimateOtherAddnLiftChargesDTO
                    );
                    return existingWorkEstimateOtherAddnLiftCharges;
                }
            )
            .map(workEstimateOtherAddnLiftChargesRepository::save)
            .map(workEstimateOtherAddnLiftChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateOtherAddnLiftChargesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateOtherAddnLiftCharges");
        return workEstimateOtherAddnLiftChargesRepository.findAll(pageable).map(workEstimateOtherAddnLiftChargesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateOtherAddnLiftChargesDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateOtherAddnLiftCharges : {}", id);
        return workEstimateOtherAddnLiftChargesRepository.findById(id).map(workEstimateOtherAddnLiftChargesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateOtherAddnLiftCharges : {}", id);
        workEstimateOtherAddnLiftChargesRepository.deleteById(id);
    }
}
