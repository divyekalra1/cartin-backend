package io.cartin.backend.service;

import io.cartin.backend.models.CartinUser;
import io.cartin.backend.repository.CartinUserRepository;
import io.cartin.backend.repository.RoleRepository;
import io.cartin.backend.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartinUserDetailsService implements UserDetailsService {

    private final CartinUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;

    @Override
    public CartinUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("user not found with email: " + username));
    }

    public String generateConfirmationToken(String username) {
        return jwtUtils.generateConfirmationTokenFromUsername(username);
    }
}
