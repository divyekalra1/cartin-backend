package io.cartin.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderItems> orderedItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private CartinUser user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderLog> orderLog = new HashSet<>();
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;

}
