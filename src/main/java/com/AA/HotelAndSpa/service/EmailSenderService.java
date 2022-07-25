package com.AA.HotelAndSpa.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String text);

    void forgotPassword(String email);
}
