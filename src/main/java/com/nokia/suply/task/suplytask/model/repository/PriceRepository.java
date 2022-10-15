package com.nokia.suply.task.suplytask.model.repository;

import com.nokia.suply.task.suplytask.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Price findByManufacturerNameAndPartName(String manufacturerName, String priceName);

    List<Price> findByPartName(String partName);

    @Query("select pc from Price pc " +
            "inner join pc.part p " +
            "inner join pc.manufacturer m " +
            "where (p.name = :partName) " +
            "and (:manufacturerName is null or m.name = : manufacturerName)")
    List<Price> findByPartNameAndManufacturerName(
            @Param("partName") String partName,
            @Param("manufacturerName") String manufacturerName);

}
