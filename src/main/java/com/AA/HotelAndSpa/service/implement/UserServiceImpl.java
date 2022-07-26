package com.AA.HotelAndSpa.service.implement;

import com.AA.HotelAndSpa.exception.PasswordNotMatchingException;
import com.AA.HotelAndSpa.exception.RecordBadRequestException;
import com.AA.HotelAndSpa.exception.RecordNotFoundException;
import com.AA.HotelAndSpa.model.user.User;
import com.AA.HotelAndSpa.repository.UserRepository;
import com.AA.HotelAndSpa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        try {
            Objects.requireNonNull(user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new RecordBadRequestException("User with this email or phone is already exist.");
        } catch (ConstraintViolationException ex) {
            throw new RecordBadRequestException(ex.getMessage());
        }
    }

    public User findById(long id) {
        Objects.requireNonNull(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("User with id:%s, not found.", id)));
    }

    public User findByEmail(String email) {
        Objects.requireNonNull(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("User with email:%s, not found.", email)));
    }

    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    public User update(long id, User updatedUser) {
        Objects.requireNonNull(id);

        User user = findById(id);
        user.setEmail(updatedUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setPhone(updatedUser.getPhone());
        user.setRoles(updatedUser.getRoles());

        return user;
    }

    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    String.format("User with id:%s, not found.", id));
        }
    }

    @Override
    public User changePassword(String newPassword, String currentPassword, String email) {
        User user = findByEmail(email);
        if (bCryptPasswordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        } else {
            throw new PasswordNotMatchingException("Your old password does not match");
        }
        return user;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

    public boolean isUserExists(Long id) {
        return userRepository.existsById(id);
    }

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}