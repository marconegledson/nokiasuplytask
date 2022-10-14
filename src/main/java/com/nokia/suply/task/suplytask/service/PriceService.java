package com.nokia.suply.task.suplytask.service;

import com.nokia.suply.task.suplytask.model.entity.Manufacturer;
import com.nokia.suply.task.suplytask.model.entity.Part;
import com.nokia.suply.task.suplytask.model.entity.Price;
import com.nokia.suply.task.suplytask.model.repository.ManufacturerRepository;
import com.nokia.suply.task.suplytask.model.repository.PartRepository;
import com.nokia.suply.task.suplytask.model.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PartRepository partRepository;

    public void listQuantity(String partName, String manufacturerName) {
        if(StringUtils.isBlank(partName)) {
            log.info("Enter the part name");
            return;
        }
        List<Price> prices = priceRepository.findByPartNameAndManufacturerName(partName, manufacturerName);

        if(CollectionUtils.isEmpty(prices)) {
            log.info("No recording found");
            return;
        }

        if(StringUtils.isBlank(manufacturerName)) {
            Price price = prices.stream().findFirst().get();
            log.info("Part name: {}", price.getPart().getName());
            log.info("Manufacturer name: {}", price.getManufacturer().getName());
            log.info("Price: {}, quantity: {}", price.getPrice(), price.getQuantity());
        } else {
            log.info("Part name: {}", partName);
            prices.stream().forEach(price -> {
                log.info("Manufacturer: {}, price: {}, quantity: {}", price.getManufacturer().getName(),
                        price.getPrice(),
                        price.getQuantity());
            });
        }
    }

    public void addPartPrice(String partName, String manufacturerName, BigDecimal priceParam, Integer quantity) {
        try {

            Part part = partRepository.findByName(partName);

            if(Objects.isNull(part)) {
                return;
            }

            Manufacturer manufacturer = manufacturerRepository.findByName(manufacturerName);

            if(Objects.isNull(manufacturer)) {
                return;
            }

            Price price = priceRepository.findByManufacturerNameAndPartName(manufacturerName, partName);

            if(Objects.nonNull(price)) {
                price.setPrice(priceParam);
                price.setQuantity(price.getQuantity() + quantity);
                priceRepository.save(price);
                log.info("Data successfully updated");
            } else {
                priceRepository.save(Price.builder().manufacturer(manufacturer)
                        .part(part)
                        .price(priceParam)
                        .quantity(quantity).build());
                log.info("Data saved successfully");
            }

        } catch (Exception e) {
            log.error("There was a problem. Nothing was made. {}", e.getMessage());
            e.getStackTrace();
        }
    }
}
