package com.nokia.suply.task.suplytask.model.repository;

import com.nokia.suply.task.suplytask.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
