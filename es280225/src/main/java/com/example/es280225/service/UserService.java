package com.example.es280225.service;

import com.example.es280225.mapper.UserMapper;
import com.example.es280225.object.dto.UserDTO;
import com.example.es280225.object.model.User;
import com.example.es280225.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toUserDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        existingUser.setNome(userDTO.getNome());
        existingUser.setEmail(userDTO.getEmail());

        existingUser = userRepository.save(existingUser);
        return userMapper.toUserDTO(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}

