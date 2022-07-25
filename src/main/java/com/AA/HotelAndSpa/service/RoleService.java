package com.AA.HotelAndSpa.service;

import com.AA.HotelAndSpa.model.user.Role;

public interface RoleService {

    Role save(Role role);

    Role findByRole(String userRole);
}
