package com.nokia.suply.task.suplytask.model.repository;

import com.nokia.suply.task.suplytask.model.entity.SupplieCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplieCompanyRepository extends JpaRepository<SupplieCompany, Long> {

    SupplieCompany findByPartNameAndManufacturerName(String partName, String manufacturerName);

    List<SupplieCompany> findAllByCompanyId(Long id);

}
