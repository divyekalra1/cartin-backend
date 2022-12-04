package io.cartin.backend.service;


import io.cartin.backend.email.EmailSender;
import io.cartin.backend.models.CartinUser;
import io.cartin.backend.models.Role;
import io.cartin.backend.models.RoleType;
import io.cartin.backend.payload.request.SigninRequest;
import io.cartin.backend.payload.request.SignupRequest;
import io.cartin.backend.payload.response.CartinUserInfoResponse;
import io.cartin.backend.repository.CartinUserRepository;
import io.cartin.backend.repository.RoleRepository;
import io.cartin.backend.security.jwt.JwtUtils;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final CartinUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final EmailSender emailSender;

    @Value("${io.cartin.baseUrl}")
    private String baseUrl;

    public ResponseEntity<?> signup(SignupRequest request) throws IllegalArgumentException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
            // Resent activation mail as another service?
        }

        CartinUser user = new CartinUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getAddress(),
                passwordEncoder.encode(request.getPassword())
        );

        Set<String> strRoles = request.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER).orElseThrow(
                    () -> new RuntimeException("Error: Role ROLE_USER is not found.")
            );
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                // Can not add an admin during signup. That can only be done by another admin.
                if ("seller".equals(role)) {
                    Role sellerRole = roleRepository.findByRoleType(RoleType.ROLE_MANAGER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(sellerRole);
                } else {
                    Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        String confirmationToken = jwtUtils.generateConfirmationTokenFromUsername(user.getUsername());
        String link = baseUrl + "api/auth/confirm?token=" + confirmationToken;

        emailSender.send("no-reply@cartin.io", request.getEmail(), buildEmail(request.getFirstName(), link));

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.
                ok()
                .body(new CartinUserInfoResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getAddress(),
                        user.getRoles().stream().map(role -> role.getRoleType().name()).collect(Collectors.toList())
                ));
    }

    @Transactional
    public ResponseEntity<?> confirmToken(String token) throws SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException {
        String email = jwtUtils.getUsernameFromJwt(token);
        CartinUser user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User with provided email not found")
        );

        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok().body(new CartinUserInfoResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getRoles().stream().map(role -> role.getRoleType().name()).collect(Collectors.toList())
        ));
    }

    public ResponseEntity<?> signin(SigninRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CartinUser user = (CartinUser) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

        List<String> roles = user.getAuthorities().stream().map(
                GrantedAuthority::getAuthority // effectively: item -> item.getAuthority()
        ).collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
                new CartinUserInfoResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getAddress(),
                        roles
                )
        );
    }

    private String buildEmail(String name, String link) {
        return "Hi " + name + "!\nClick the following link to verify your account:\n" + link;
    }
}
