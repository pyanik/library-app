package com.app.library.service;

import com.app.library.exception.EmailAlreadyExistsException;
import com.app.library.model.dto.UserInfoDto;
import com.app.library.model.dto.UserDto;
import com.app.library.model.entity.UserEntity;
import com.app.library.model.mapper.UserMapper;
import com.app.library.persistence.UserRepository;
import lombok.AllArgsConstructor;
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

    public List<UserInfoDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserInfoDto)
                .toList();
    }

    public Optional<UserInfoDto> getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::toUserInfoDto);
    }

    public UserInfoDto saveUser(UserDto userDto) {
        UserEntity userToSave = userMapper.toEntity(userDto);
        String userEmail = userToSave.getEmail();
        if (userRepository.existsByEmail(userEmail)) {
            throw new EmailAlreadyExistsException(userEmail);
        }

        String encodedPassword = passwordEncoder.encode(userToSave.getPassword());
        userToSave.setPassword(encodedPassword);

        UserEntity savedUser = userRepository.save(userToSave);
        return userMapper.toUserInfoDto(savedUser);

    }

    public Optional<UserInfoDto> replaceUser(UUID userId, UserDto userDto) {
        if (!userRepository.existsById(userId)) {
            return Optional.empty();
        }
        UserEntity userToUpdate = userMapper.toEntity(userDto);
        UserEntity updatedUser = userRepository.save(userToUpdate);
        return Optional.of(userMapper.toUserInfoDto(updatedUser));
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
