package com.AA.HotelAndSpa.service;

import com.AA.HotelAndSpa.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {
    User save(User user);

    User findById(long id);

    User findByEmail(String email);

    Set<User> findAll();

    User update(long id, User updatedUser);

    void deleteById(long id);

    User changePassword(String newPassword, String currentPassword, String email);
}
