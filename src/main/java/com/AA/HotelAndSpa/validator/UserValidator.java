package com.AA.HotelAndSpa.validator;

import com.AA.HotelAndSpa.exception.RecordNotFoundException;
import com.AA.HotelAndSpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void userExistById(Long id) {
        Objects.requireNonNull(id);
        if (!userRepository.existsById(id)) {
            throw new RecordNotFoundException("User with id:%s, don't exists.");
        }
    }

    public void emailDuplicate(String email) {
        Objects.requireNonNull(email);
        if (!userRepository.existsByEmail(email)) {
            throw new RecordNotFoundException("User with email:%s, don't exists.");
        }
    }
}
