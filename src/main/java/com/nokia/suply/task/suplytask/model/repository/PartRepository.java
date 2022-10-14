package com.nokia.suply.task.suplytask.model.repository;

import com.nokia.suply.task.suplytask.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Part findByName(String name);

}
