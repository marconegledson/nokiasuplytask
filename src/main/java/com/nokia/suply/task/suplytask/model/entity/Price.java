package com.nokia.suply.task.suplytask.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_price")
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Price implements Persistable<Long> {

    @Id
    @Column(name = "id_price")
    @SequenceGenerator(name = "seq_price", sequenceName = "seq_price", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_price")
    private Long id;

    @Column(name = "quantity_price")
    private Integer quantity;

    @Column(name = "price_price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_part")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "id_manufacturer")
    private Manufacturer manufacturer;

    @Transient
    private boolean pay;

    @Transient
    private Integer quantityPay;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
