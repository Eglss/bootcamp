package com.AA.HotelAndSpa.dto.exceptionMessage;

import lombok.Getter;

@Getter
public class BadRequestMessageDto {

    private String message;
    private ErrorFieldDto errors;
}