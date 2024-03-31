package com.system.bank.controller;

import com.system.bank.model.ResponseModel;
import com.system.bank.model.authentication.AuthenticationResponseModel;
import com.system.bank.model.authentication.LoginRequestModel;
import com.system.bank.model.authentication.RegisterRequestModel;
import com.system.bank.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<ResponseModel> register(@Valid @RequestBody RegisterRequestModel request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.CREATED)
                                .success(true)
                                .data(authenticationService.register(request))
                                .build()
                );
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseModel> login(@Valid @RequestBody LoginRequestModel request) {
        return ResponseEntity.ok(
                ResponseModel
                        .builder()
                        .status(HttpStatus.OK)
                        .success(true)
                        .data(authenticationService.login(request))
                        .build()
        );
    }
}
