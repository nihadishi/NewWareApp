//package com.example.warehouseapp.controller;
//
//import com.example.warehouseapp.model.authDto.AuthRequestDto;
//import com.example.warehouseapp.model.authDto.AuthenticationDto;
//import com.example.warehouseapp.model.authDto.UserRegisterRequestDto;
//import com.example.warehouseapp.model.responseDto.SimpleMessageDto;
//import com.example.warehouseapp.service.auth.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/v1/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final AuthService authService;
//
//    @PostMapping("/register")
//    public SimpleMessageDto<String> register(
//            @RequestBody UserRegisterRequestDto requestDto
//    ) {
//        authService.register(requestDto);
//        return new SimpleMessageDto<>(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value());
//    }
//
//    @PostMapping("/login")
//    public SimpleMessageDto<AuthenticationDto> register(
//            @RequestBody AuthRequestDto authRequestDto
//    ) {
//
//        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), authService.authenticate(authRequestDto));
//
//    }
//}

package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.authDto.AuthRequestDto;
import com.example.warehouseapp.model.authDto.AuthenticationDto;
import com.example.warehouseapp.model.authDto.UserRegisterRequestDto;
import com.example.warehouseapp.model.responseDto.SimpleMessageDto;
import com.example.warehouseapp.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public SimpleMessageDto<String> register(
            @RequestBody UserRegisterRequestDto requestDto
    ) {
        authService.register(requestDto);
        return new SimpleMessageDto<>(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value());
    }

    @PostMapping(value = "/login", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public SimpleMessageDto<AuthenticationDto> login(
            @RequestBody AuthRequestDto authRequestDto
    ) {

        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), authService.authenticate(authRequestDto));

    }
}