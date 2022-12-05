package io.cartin.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive
    private Long quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
    @ManyToOne
    private Order order;

    private Float offerApplied;

}
