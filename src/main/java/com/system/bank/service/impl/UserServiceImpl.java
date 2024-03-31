package com.system.bank.service.impl;

import com.system.bank.entity.User;
import com.system.bank.model.authentication.UserProfileResponseModel;
import com.system.bank.repository.UserRepository;
import com.system.bank.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserProfileResponseModel getUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return new UserProfileResponseModel(user.getName(),user.getPhone(),user.getEmail());
    }
}