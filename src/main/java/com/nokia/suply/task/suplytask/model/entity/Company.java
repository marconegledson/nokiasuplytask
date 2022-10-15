package com.nokia.suply.task.suplytask.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tb_company")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Company implements Persistable<Long> {
    @Id
    @Column(name = "id_company")
    @SequenceGenerator(name = "seq_company", sequenceName = "seq_company", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_company")
    private Long id;

    @Column(name = "money")
    private BigDecimal money = BigDecimal.ZERO;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
