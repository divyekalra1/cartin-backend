package io.cartin.backend.controller;

import io.cartin.backend.models.CartinUser;
import io.cartin.backend.payload.request.SigninRequest;
import io.cartin.backend.payload.request.SignupRequest;
import io.cartin.backend.payload.response.CartinUserInfoResponse;
import io.cartin.backend.payload.response.MessageResponse;
import io.cartin.backend.service.AuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(path = "signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest signinRequest) {
        return authenticationService.signin(signinRequest);
    }

    @PostMapping(path = "signup")
    private ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        return authenticationService.signup(signupRequest);
    }

    @GetMapping(path = "confirm")
    private ResponseEntity<?> confirm(@RequestParam("token") String token) {
        return authenticationService.confirmToken(token);
    }
}
