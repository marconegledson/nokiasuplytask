package com.nokia.suply.task.suplytask.service;

import com.nokia.suply.task.suplytask.model.entity.SupplieCompany;
import com.nokia.suply.task.suplytask.model.repository.SupplieCompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SupplieCompanyService {

    private final SupplieCompanyRepository supplieCompanyRepository;

    public void save(SupplieCompany supplieCompany) {
        supplieCompanyRepository.save(supplieCompany);
    }

    public void saveAll(List<SupplieCompany> supplieCompanyList) {
        supplieCompanyRepository.saveAll(supplieCompanyList);
    }
    public SupplieCompany findByPartNameAndManufacturerName(String partName, String manufacturerName) {
        return supplieCompanyRepository.findByPartNameAndManufacturerName(partName, manufacturerName);
    }

    public List<SupplieCompany> findAllByCompanyId(Long companyId) {
        return supplieCompanyRepository.findAllByCompanyId(companyId);
    }

}
