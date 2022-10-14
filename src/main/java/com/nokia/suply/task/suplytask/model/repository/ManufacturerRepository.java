package com.nokia.suply.task.suplytask.model.repository;

import com.nokia.suply.task.suplytask.model.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Manufacturer findByName(String name);

    void deleteByName(String name);

}
