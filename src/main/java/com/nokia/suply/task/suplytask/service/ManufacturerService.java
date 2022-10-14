package com.nokia.suply.task.suplytask.service;

import com.nokia.suply.task.suplytask.model.entity.Manufacturer;
import com.nokia.suply.task.suplytask.model.repository.ManufacturerRepository;
import com.nokia.suply.task.suplytask.model.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    private final PriceRepository priceRepository;

    public void addManufacturer(String name) {
        try {

            Manufacturer manufacturer = manufacturerRepository.findByName(name);

            if(Objects.nonNull(manufacturer) && StringUtils.equals(name, manufacturer.getName())) {
                log.info("This manufacturer already exists");
            } else {
                manufacturerRepository.save(Manufacturer.builder().name(name).build());
                log.info("Manufacturer added successfully");
            }

        } catch (Exception e) {
            log.info("There was a problem. Manufacturer not added");
        }
    }

    public void deleteManufacturer(String name) {
        try {
            Manufacturer manufacturer = manufacturerRepository.findByName(name);
            if(Objects.nonNull(manufacturer)) {
                manufacturerRepository.deleteByName(name);
                log.info("Manufacturer deleted");
            } else {
                log.info("Manufacturer not found");
            }
        } catch (Exception e) {
            log.info("There was a problem. Nothing was made");
        }
    }
}
