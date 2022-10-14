package com.nokia.suply.task.suplytask.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;


@Getter @Setter
@Entity
@Table(name = "dbteste.tb_part")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Part implements Persistable<Long> {

    @Id
    @Column(name = "id_part")
    @SequenceGenerator(name = "seq_part", sequenceName = "seq_part", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_part")
    private Long id;

    @NotBlank
    @Column(name = "name_part", unique = true)
    private String name;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
