package com.nokia.suply.task.suplytask.service;

import com.nokia.suply.task.suplytask.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {

}
