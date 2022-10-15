package com.nokia.suply.task.suplytask.service;

import com.nokia.suply.task.suplytask.model.entity.Company;
import com.nokia.suply.task.suplytask.model.entity.Price;
import com.nokia.suply.task.suplytask.model.entity.SupplieCompany;
import com.nokia.suply.task.suplytask.model.repository.CompanyRepository;
import com.nokia.suply.task.suplytask.model.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PriceRepository priceRepository;
    private final PriceService priceService;
    private final SupplieCompanyService supplieCompanyService;

    public Company findFirstCompany() {
        return companyRepository.findFirstByIdNotNull();
    }

    public void listPart() {
        Company company = findFirstCompany();
        List<SupplieCompany> supplieCompanyList = supplieCompanyService.findAllByCompanyId(company.getId());

        if(CollectionUtils.isEmpty(supplieCompanyList)) {
            log.info("No parts list found");
        } else {
            supplieCompanyList.stream().forEach(item -> {
                log.info("> Part: {}, manufacturer: {}, quantity: {}",
                        item.getPart().getName(), item.getManufacturer().getName(), item.getQuantityPurchased());
            });
        }
    }

    public void addMoney(BigDecimal moneyParam) {
        try {
            Company company = findFirstCompany();
            company.setMoney(company.getMoney().add(moneyParam));
            companyRepository.save(company);
            log.info("Currente balance: {}", company.getMoney());
        }catch (Exception e) {
            log.error("There was a problem. Nothing was made. {}", e.getMessage());
        }
    }

    public void buyParts(String partName, AtomicReference<Integer> quantity) {
        try {
            Integer quantityMemory = quantity.get();
            Company company = findFirstCompany();
            List<Price> prices = priceService.findByPartName(partName);
            List<Price> pricesMemory = priceService.findByPartName(partName);

            /**
             * Verificar se há peças com esse nome
             * */
            if(CollectionUtils.isEmpty(prices)) {
                log.info("Part not found");
                return;
            }

            Integer totalQuantityAvailable = prices.stream()
                    .map(Price::getQuantity).reduce(0, Integer::sum);

            /**
             * Verificar se há quantidade de peças disponiveis
             * */
            if(totalQuantityAvailable < quantity.get()) {
                log.info("No number of parts available");
                return;
            }

            processSalesCalculation(quantity, prices);

            AtomicReference<BigDecimal> spent = calculateTotalPurchase(prices);

            company.setMoney(company.getMoney().subtract(spent.get()));

            List<SupplieCompany> supplieCompanyList = createObjectListSupplieCompany(company, prices);

            saveSales(supplieCompanyList);

            companyRepository.save(company);

            priceRepository.saveAll(prices);

            createLogReturn(partName, quantityMemory, prices, pricesMemory, spent);

        } catch (Exception e) {
            log.error("There was a problem. Nothing was made. {}", e.getMessage());
        }

    }

    public void addCompany() {
        Company company = companyRepository.findFirstByIdNotNull();
        if(Objects.isNull(company)) {
            companyRepository.save(Company.builder().money(BigDecimal.ZERO).build());
        }
    }

    private static void createLogReturn(String partName, Integer quantityMemory, List<Price> prices, List<Price> pricesMemory, AtomicReference<BigDecimal> spent) {
        log.info("- There is:");
        pricesMemory.stream().filter(ft -> ft.getQuantity() > 0).forEach(price -> {
            log.info("  - Part: {}, manufacturer: {}, price: {}, quantity: {}",
                    price.getPart().getName(), price.getManufacturer().getName(),
                    price.getPrice(), price.getQuantity());
        });
        log.info("> Part name: {}", partName);
        log.info("> Quantity: {}", quantityMemory);
        prices.stream().filter(ft -> ft.isPay()).forEach(price -> {
            log.info("  - {} quantity is taken from {} and added to the company",
                    price.getQuantityPay(), price.getManufacturer().getName());
        });
        log.info("  - Spent: {}", spent.get());
    }

    private void saveSales(List<SupplieCompany> supplieCompanyList) {
        supplieCompanyList.stream().forEach(item -> {
            SupplieCompany supplieCompany = supplieCompanyService
                    .findByPartNameAndManufacturerName(item.getPart().getName(), item.getManufacturer().getName());
            if(Objects.nonNull(supplieCompany)) {
                supplieCompany.setQuantityPurchased(supplieCompany.getQuantityPurchased() + item.getQuantityPurchased());
                supplieCompanyService.save(supplieCompany);
            } else {
                supplieCompanyService.save(item);
            }
        });
    }

    private static List<SupplieCompany> createObjectListSupplieCompany(Company company, List<Price> prices) {
        List<SupplieCompany> supplieCompanyList = prices.stream().filter(ft -> ft.isPay()).map(item -> SupplieCompany.builder()
                        .price(item)
                        .part(item.getPart())
                        .manufacturer(item.getManufacturer())
                        .company(company)
                        .quantityPurchased(item.getQuantityPay())
                        .build())
                .collect(Collectors.toList());
        return supplieCompanyList;
    }

    private static AtomicReference<BigDecimal> calculateTotalPurchase(List<Price> prices) {
        AtomicReference<BigDecimal> spent = new AtomicReference<>(BigDecimal.ZERO);

        prices.stream().filter(ft -> ft.isPay()).forEach(item -> {
            spent.set(spent.get().add(item.getPrice().multiply(
                    new BigDecimal(item.getQuantityPay())).setScale(2, RoundingMode.HALF_EVEN)));
        });
        return spent;
    }

    private static void processSalesCalculation(AtomicReference<Integer> quantity, List<Price> prices) {
        prices.stream().forEach(price -> {
            if(!Objects.equals(quantity.get(), 0)){
                if(quantity.get() >= price.getQuantity()) {
                    price.setQuantityPay(price.getQuantity());
                    quantity.set(quantity.get() - price.getQuantity());
                    price.setQuantity(0);
                } else {
                    price.setQuantityPay(quantity.get());
                    quantity.set(price.getQuantity() - quantity.get());
                    price.setQuantity(quantity.get());
                    quantity.set(0);
                }

                price.setPay(true);
            }
        });
    }
}
