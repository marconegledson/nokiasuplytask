package com.nokia.suply.task.suplytask.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_part")
@Getter
@Setter
@RequiredArgsConstructor
public class Part implements Persistable<Long> {

    @Id
    @Column(name = "id_part")
    @SequenceGenerator(name = "seq_part", sequenceName = "seq_part", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_part")
    private Long id;

    @NotBlank
    @Column(name = "name_part", unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
    private List<Manufacturer> manufacturers = List.of();

    @Override
    public boolean isNew() {
        return id == null;
    }
}
