package com.AA.HotelAndSpa.service.implement;

import com.AA.HotelAndSpa.converter.UserConverter;
import com.AA.HotelAndSpa.dto.user.LoginRequest;
import com.AA.HotelAndSpa.dto.user.LoginResponse;
import com.AA.HotelAndSpa.dto.user.UserResponse;
import com.AA.HotelAndSpa.exception.RecordBadRequestException;
import com.AA.HotelAndSpa.model.user.User;
import com.AA.HotelAndSpa.security.JwtTokenUtil;
import com.AA.HotelAndSpa.service.LoginService;
import com.AA.HotelAndSpa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserConverter userConverter;

    public LoginResponse authenticate(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new RecordBadRequestException("Invalid Credentials.");
        }

        User user = (User) userService.loadUserByUsername(loginRequest.getEmail());
        UserResponse userResponse = userConverter.convert(user);

        return LoginResponse.builder()
                .token(jwtTokenUtil.generateToken(user))
                .user(userResponse)
                .build();
    }
}
