package com.system.bank.service.impl;

import com.system.bank.entity.Role;
import com.system.bank.entity.User;
import com.system.bank.model.authentication.AuthenticationResponseModel;
import com.system.bank.model.authentication.LoginRequestModel;
import com.system.bank.model.authentication.RegisterRequestModel;
import com.system.bank.repository.UserRepository;
import com.system.bank.security.JwtService;
import com.system.bank.service.AuthenticationService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponseModel register(RegisterRequestModel request) {

        if(isEmailOrPhoneAlreadyExists(request.getEmail(),request.getPhone())){
           throw new EntityExistsException("Email or phone already exists");
       }
        User user = new User();

        toEntity(user,request);

        userRepository.save(user);

        return new AuthenticationResponseModel(jwtService.generateToken(user));
    }

    @Override
    public AuthenticationResponseModel login(LoginRequestModel request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("user " + request.getEmail() + " not found"));

        return new AuthenticationResponseModel(jwtService.generateToken(user));
    }


    private boolean isEmailOrPhoneAlreadyExists (String email , String phone){
        return userRepository.existsByEmail(email) || userRepository.existsByPhone(phone);
    }
    public void toEntity(User user, RegisterRequestModel request){
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
    }
}
