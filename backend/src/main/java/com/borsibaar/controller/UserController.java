package com.borsibaar.controller;

import com.borsibaar.dto.UserSummaryResponseDto;
import com.borsibaar.entity.User;
import com.borsibaar.mapper.UserMapper;
import com.borsibaar.repository.UserRepository;
import com.borsibaar.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserSummaryResponseDto>> getOrganizationUsers(
            @CookieValue(name = "jwt", required = false) String token) {
        User currentUser = authenticateUser(token);
        checkAdminRole(currentUser);

        List<UserSummaryResponseDto> users = userRepository.findByOrganizationId(currentUser.getOrganizationId())
                .stream()
                .map(userMapper::toSummaryDto)
                .toList();

        return ResponseEntity.ok(users);
    }

    private User authenticateUser(String token) {
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
        }
        Claims claims = jwtService.parseToken(token);
        User user = userRepository.findByEmail(claims.getSubject())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        if (user.getOrganizationId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has no organization");
        }
        return user;
    }

    private void checkAdminRole(User user) {
        if (user.getRole() == null || !"ADMIN".equals(user.getRole().getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin role required");
        }
    }
}

