package com.dxc.eproc.rateanalysis.service.impl;

import com.dxc.eproc.rateanalysis.domain.WorkEstimateRoyalty;
import com.dxc.eproc.rateanalysis.repository.WorkEstimateRoyaltyRepository;
import com.dxc.eproc.rateanalysis.service.WorkEstimateRoyaltyService;
import com.dxc.eproc.rateanalysis.service.dto.WorkEstimateRoyaltyDTO;
import com.dxc.eproc.rateanalysis.service.mapper.WorkEstimateRoyaltyMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkEstimateRoyalty}.
 */
@Service
@Transactional
public class WorkEstimateRoyaltyServiceImpl implements WorkEstimateRoyaltyService {

    private final Logger log = LoggerFactory.getLogger(WorkEstimateRoyaltyServiceImpl.class);

    private final WorkEstimateRoyaltyRepository workEstimateRoyaltyRepository;

    private final WorkEstimateRoyaltyMapper workEstimateRoyaltyMapper;

    public WorkEstimateRoyaltyServiceImpl(
        WorkEstimateRoyaltyRepository workEstimateRoyaltyRepository,
        WorkEstimateRoyaltyMapper workEstimateRoyaltyMapper
    ) {
        this.workEstimateRoyaltyRepository = workEstimateRoyaltyRepository;
        this.workEstimateRoyaltyMapper = workEstimateRoyaltyMapper;
    }

    @Override
    public WorkEstimateRoyaltyDTO save(WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO) {
        log.debug("Request to save WorkEstimateRoyalty : {}", workEstimateRoyaltyDTO);
        WorkEstimateRoyalty workEstimateRoyalty = workEstimateRoyaltyMapper.toEntity(workEstimateRoyaltyDTO);
        workEstimateRoyalty = workEstimateRoyaltyRepository.save(workEstimateRoyalty);
        return workEstimateRoyaltyMapper.toDto(workEstimateRoyalty);
    }

    @Override
    public Optional<WorkEstimateRoyaltyDTO> partialUpdate(WorkEstimateRoyaltyDTO workEstimateRoyaltyDTO) {
        log.debug("Request to partially update WorkEstimateRoyalty : {}", workEstimateRoyaltyDTO);

        return workEstimateRoyaltyRepository
            .findById(workEstimateRoyaltyDTO.getId())
            .map(
                existingWorkEstimateRoyalty -> {
                    workEstimateRoyaltyMapper.partialUpdate(existingWorkEstimateRoyalty, workEstimateRoyaltyDTO);
                    return existingWorkEstimateRoyalty;
                }
            )
            .map(workEstimateRoyaltyRepository::save)
            .map(workEstimateRoyaltyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkEstimateRoyaltyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkEstimateRoyalties");
        return workEstimateRoyaltyRepository.findAll(pageable).map(workEstimateRoyaltyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEstimateRoyaltyDTO> findOne(Long id) {
        log.debug("Request to get WorkEstimateRoyalty : {}", id);
        return workEstimateRoyaltyRepository.findById(id).map(workEstimateRoyaltyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkEstimateRoyalty : {}", id);
        workEstimateRoyaltyRepository.deleteById(id);
    }
}
