package dev.freddxant.api.auth.service;

import dev.freddxant.api.auth.dto.AuthResponse;
import dev.freddxant.api.auth.dto.RegisterRequest;
import dev.freddxant.api.auth.security.CustomUserDetails;
import dev.freddxant.api.common.contant.Role;
import dev.freddxant.api.common.security.JwtService;
import dev.freddxant.api.user.entity.User;
import dev.freddxant.api.user.repository.RoleRepository;
import dev.freddxant.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        var role = roleRepository.findById(Role.USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .isLocked(false)
                .role(role)
                .build();
        userRepository.save(user);
    }

    public AuthResponse login(RegisterRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtService.generateToken(userDetails);

        return  new AuthResponse(jwt);
    }
}
