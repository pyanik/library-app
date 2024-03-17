package com.app.library.service;

import com.app.library.exception.BookAlreadyBorrowedException;
import com.app.library.exception.EmailAlreadyExistsException;
import com.app.library.model.dto.UserDto;
import com.app.library.model.entity.UserEntity;
import com.app.library.model.mapper.UserMapper;
import com.app.library.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public Optional<UserDto> getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto);
    }

    public UserDto saveUser(UserDto userDto) {
        UserEntity userToSave = userMapper.toEntity(userDto);
        String userEmail = userToSave.getEmail();
        if (userRepository.existsByEmail(userEmail)) {
            throw new EmailAlreadyExistsException(userEmail);
        }

        String encodedPassword = passwordEncoder.encode(userToSave.getPassword());
        userToSave.setPassword(encodedPassword);

        UserEntity savedUser = userRepository.save(userToSave);
        return userMapper.toDto(savedUser);

    }

    public Optional<UserDto> replaceUser(UUID userId, UserDto userDto) {
        if (!userRepository.existsById(userId)) {
            return Optional.empty();
        }
        UserEntity userToUpdate = userMapper.toEntity(userDto);
        UserEntity updatedUser = userRepository.save(userToUpdate);
        return Optional.of(userMapper.toDto(updatedUser));
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
