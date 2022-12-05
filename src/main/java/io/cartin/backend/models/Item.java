package io.cartin.backend.models;

import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotEmpty
        @NotBlank
        private String name;
        @NotBlank
        @NotEmpty
        private String brand;
        @Positive
        private Float price;
        @PositiveOrZero
        private Long availableQuantity;
        @BooleanFlag
        private boolean availability;





}
