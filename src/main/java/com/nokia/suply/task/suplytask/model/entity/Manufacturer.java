package com.nokia.suply.task.suplytask.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_manufacturer")
@Getter @Setter
@RequiredArgsConstructor
public class Manufacturer implements Persistable<Long> {

    @Id
    @Column(name = "id_manufacturer")
    @SequenceGenerator(name = "seq_manufacturer", sequenceName = "seq_manufacturer", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manufacturer")
    private Long id;

    @NotBlank
    @Column(name = "name_manufacturer", unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name =  "id_part", foreignKey = @ForeignKey(name =  "fk__manufacturer_id_part"), nullable = false)
    private Part part;

    @Override
    public boolean isNew() {
        return id == null;
    }
    
}
