package com.AA.HotelAndSpa.service;

import com.AA.HotelAndSpa.dto.user.LoginRequest;
import com.AA.HotelAndSpa.dto.user.LoginResponse;

public interface LoginService {

    LoginResponse authenticate(LoginRequest loginRequest);
}
