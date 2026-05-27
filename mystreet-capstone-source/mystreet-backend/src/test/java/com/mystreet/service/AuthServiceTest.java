package com.mystreet.service;

import com.mystreet.dto.AuthDtos.RegisterRequest;
import com.mystreet.repository.UserRepository;
import com.mystreet.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthServiceTest {
    @Test
    void register_success() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        JwtUtil jwt = Mockito.mock(JwtUtil.class);
        when(repo.existsByEmail("user@test.com")).thenReturn(false);
        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(jwt.generateToken("user@test.com", false)).thenReturn("token");
        AuthService service = new AuthService(repo, new BCryptPasswordEncoder(), jwt);
        var res = service.register(new RegisterRequest("user@test.com", "pass123"));
        assertThat(res.token()).isEqualTo("token");
        assertThat(res.admin()).isFalse();
    }
}
