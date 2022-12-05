package io.cartin.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty
    @ManyToOne
    OrderStatus orderStatus;

    @ManyToOne
    private Order order;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt; //orderStatusTimeStamp

}
