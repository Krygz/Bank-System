package com.system.bank.service;

import com.system.bank.model.authentication.AuthenticationResponseModel;
import com.system.bank.model.authentication.LoginRequestModel;
import com.system.bank.model.authentication.RegisterRequestModel;

public interface AuthenticationService {
    AuthenticationResponseModel register(RegisterRequestModel request);
    AuthenticationResponseModel login(LoginRequestModel request);

}
