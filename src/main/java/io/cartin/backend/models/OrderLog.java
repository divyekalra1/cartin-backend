package io.cartin.backend.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OrderLog {

    @NotBlank
    @NotEmpty
    private OrderStatus orderStatus;


    private LocalDateTime orderStatusTimeStamp;

}
