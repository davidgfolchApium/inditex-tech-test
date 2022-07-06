package com.inditex.techtest.repo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product_price")
@Builder(builderMethodName = "of")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductPriceEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private Long brandId;
    @Column
    private Long productId;
    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime endDate;
    @Column
    private Long tariff;
    @Column
    private Long priority;
    @Column
    private Double price;
    @Column
    private String currency;
}
