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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_supplie_company")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SupplieCompany implements Persistable<Long> {

    @Id
    @Column(name = "id_supplie")
    @SequenceGenerator(name = "seq_supplie", sequenceName = "seq_supplie", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_supplie")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_price")
    private Price price;

    @ManyToOne
    @JoinColumn(name = "part_id_part")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id_manufacturer")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;

    @Column(name = "quantity_purchased")
    private Integer quantityPurchased;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
