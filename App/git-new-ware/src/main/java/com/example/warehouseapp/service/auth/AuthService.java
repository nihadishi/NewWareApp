//package com.example.warehouseapp.service.auth;
//
//import com.example.warehouseapp.dao.entity.UserEntity;
//import com.example.warehouseapp.dao.repository.UserRepository;
//import com.example.warehouseapp.model.authDto.AuthRequestDto;
//import com.example.warehouseapp.model.authDto.AuthenticationDto;
//import com.example.warehouseapp.model.authDto.UserRegisterRequestDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class AuthService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authManager;
//
//    public void register(UserRegisterRequestDto requestDto) {
//        log.info("test");
//
//        UserEntity.builder()
//                .fullName(requestDto.getFullName())
//                .email(requestDto.getEmail())
//                .password(passwordEncoder.encode(requestDto.getPassword()))
//                .build();
//        log.info("test end");
//
//
//    }
//
//    public AuthenticationDto authenticate(AuthRequestDto authRequestDto) {
//        authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authRequestDto.getEmail(),
//                        authRequestDto.getPassword()
//                )
//        );
//
//        UserEntity user = userRepository.findUserByEmail(authRequestDto.getEmail()).orElseThrow();
//
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//
//        return AuthenticationDto.builder()
//                .token(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//
//    }
//
//    public static UserEntity getUser() {
//        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }
//}

package com.example.warehouseapp.service.auth;

import com.example.warehouseapp.dao.entity.UserEntity;
import com.example.warehouseapp.dao.repository.UserRepository;
import com.example.warehouseapp.model.authDto.AuthRequestDto;
import com.example.warehouseapp.model.authDto.AuthenticationDto;
import com.example.warehouseapp.model.authDto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public void register(UserRegisterRequestDto requestDto) {
        log.info("RegAuth TEST --->>>", requestDto.getPassword());
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        log.info("endcoded password budur:",encodedPassword);
        UserEntity user =  UserEntity.builder()
                .fullName(requestDto.getFullName())
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .build();

        userRepository.save(user);

        log.info("RegAuth end --->>>");


    }

    public AuthenticationDto authenticate(AuthRequestDto authRequestDto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()
                )
        );

        UserEntity user = userRepository.findUserByEmail(authRequestDto.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationDto.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    public static UserEntity getUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

