package io.cartin.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<OrderItems> orderedItems = new HashSet<>();

    @OneToOne
    private CartinUser user;

    @OneToOne
    private OrderLog orderLog;

    private String customerName;
    private String customerAddress;
    private String sellerName;
    private String sellerAddress;
    private Float totalAmount;
    private Timestamp dateOrderedCreated;




    private 
}
