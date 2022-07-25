package com.AA.HotelAndSpa.dto.exceptionMessage;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorFieldDto {

    private List<String> field_name;
}