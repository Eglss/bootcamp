package com.AA.HotelAndSpa.dto.user;

import lombok.Getter;

@Getter
public class ResetPasswordDto {

    private String newPassword;
    private String currentPassword;
    private String email;
}
