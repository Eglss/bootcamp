package com.AA.HotelAndSpa.converter;

import com.AA.HotelAndSpa.dto.user.UserResponse;
import com.AA.HotelAndSpa.dto.user.UserSaveRequest;
import com.AA.HotelAndSpa.dto.user.UserUpdateRequest;
import com.AA.HotelAndSpa.model.user.Role;
import com.AA.HotelAndSpa.model.user.User;
import com.AA.HotelAndSpa.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Component
@AllArgsConstructor
public class UserConverter {

    private final RoleService roleService;
    private final RoleConverter roleConverter;

    public User convert(UserSaveRequest userSaveRequest) {
        String role = "";
        for (String name : userSaveRequest.getRoles()) {
            role = name;
        }

        role = roleConverter.convertRoleRequest(role);
        Role foundRole = roleService.findByRole(role);

        Set<Role> roles = new HashSet<>();
        roles.add(foundRole);

        return User.builder()
                .name(userSaveRequest.getName())
                .surname(userSaveRequest.getSurname())
                .phone(userSaveRequest.getPhone())
                .email(userSaveRequest.getEmail())
                .password(userSaveRequest.getPassword())
                .roles(roles)
                .build();
    }

    public UserResponse convert(User user) {
        String role = "";
        for (Role roles : user.getRoles()) {
            role = roles.getRole();
        }

        role = roleConverter.convertToRoleResponse(role);
        Set<String> roles = new HashSet<>();
        roles.add(role);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .roles(roles)
                .created(user.getCreated().toString())
                .build();
    }

    public User convert(UserUpdateRequest userUpdateRequest) {
        String role = "";
        for (String name : userUpdateRequest.getRoles()) {
            role = name;
        }

        role = roleConverter.convertRoleRequest(role);
        Role foundRole = roleService.findByRole(role);

        Set<Role> roles = new HashSet<>();
        roles.add(foundRole);

        return User.builder()
                .email(userUpdateRequest.getEmail())
                .password(userUpdateRequest.getPassword())
                .name(userUpdateRequest.getName())
                .surname(userUpdateRequest.getSurname())
                .phone(userUpdateRequest.getPhone())
                .roles(roles)
                .build();
    }
}
