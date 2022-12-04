package io.cartin.backend.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    private String firstName;
    private String lastName;

    @Email
    @NotEmpty
    @NotBlank
    private String email;

    private String phoneNumber;
    private String address;
    private Set<String> roles;

    @NotEmpty
    @NotBlank
    private String password;
}
