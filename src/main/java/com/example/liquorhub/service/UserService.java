package com.example.liquorhub.service;

import com.example.liquorhub.dto.UserDto;
import com.example.liquorhub.entity.User;
import com.example.liquorhub.mapper.UserMapper;
import com.example.liquorhub.repository.UserRepository;
import com.example.liquorhub.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CartItemService cartItemService;

    public UserDto addUser(UserDto userDTO) {
        UserValidator.validateCreateUser(userDTO);
        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        UserValidator.validateExistingUser(existingUser, userDTO.getEmail());
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        UserValidator.validateUser(id, user);
        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDTO) {
        User user = userRepository.findById(id).orElse(null);
        UserValidator.validateUser(id, user);
        UserValidator.validateUpdateUser(userDTO);
        User updatedUser = userMapper.toEntity(userDTO);
        userMapper.updateUserFromDto(userDTO, updatedUser, id);
        userRepository.save(updatedUser);
        return userMapper.toDto(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        UserValidator.validateUser(id, user);
        cartItemService.clearCartByUser(user);
        user.setIsActive(Boolean.FALSE);
        userRepository.save(user);
    }

}
