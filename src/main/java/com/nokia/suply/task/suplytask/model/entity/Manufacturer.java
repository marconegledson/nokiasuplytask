package com.nokia.suply.task.suplytask.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_manufacturer")
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Manufacturer implements Persistable<Long> {

    @Id
    @Column(name = "id_manufacturer")
    @SequenceGenerator(name = "seq_manufacturer", sequenceName = "seq_manufacturer", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manufacturer")
    private Long id;

    @NotBlank
    @Column(name = "name_manufacturer", unique = true)
    private String name;

    @Override
    public boolean isNew() {
        return id == null;
    }
    
}
