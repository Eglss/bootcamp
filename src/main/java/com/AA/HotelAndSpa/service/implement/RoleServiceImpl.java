package com.AA.HotelAndSpa.service.implement;

import com.AA.HotelAndSpa.exception.RecordBadRequestException;
import com.AA.HotelAndSpa.exception.RecordNotFoundException;
import com.AA.HotelAndSpa.model.user.Role;
import com.AA.HotelAndSpa.repository.RoleRepository;
import com.AA.HotelAndSpa.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public Role save(Role role) {
        try {
            Objects.requireNonNull(role);
            return roleRepository.save(role);
        } catch (DataIntegrityViolationException ex) {
            throw new RecordBadRequestException(
                    String.format("UserRole with name: %s, is already exist.", role.getRole()));
        }
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role).orElseThrow(
                () -> new RecordNotFoundException(
                        String.format("Role with name:%s, not found", role)));
    }
}
