package io.cartin.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class CartinUserInfoResponse { // Could be replaced with a record?
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    private String phoneNumber;
    private String address;
    private List<String> roles;
}
