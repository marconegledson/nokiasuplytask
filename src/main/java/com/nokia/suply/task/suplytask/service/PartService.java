package com.nokia.suply.task.suplytask.service;

import com.nokia.suply.task.suplytask.model.entity.Part;
import com.nokia.suply.task.suplytask.model.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class PartService {

    private final PartRepository partRepository;

    public void addPart(String name) {
        try {
            partRepository.save(Part.builder().name(name).build());
            log.info("Part added successfully");
        } catch (Exception e) {
            log.info("There was a problem. Nothing was made");
        }
    }
}
