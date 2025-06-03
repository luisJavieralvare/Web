package com.unac.salescarpremium.services.impl;

import com.unac.salescarpremium.dtos.UserDTO;
import com.unac.salescarpremium.entities.User;
import com.unac.salescarpremium.repositories.UserRepository;
import com.unac.salescarpremium.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        // Mapeamos el DTO (que ya trae el ID desde Postman) a la entidad
        User user = modelMapper.map(userDTO, User.class);

        // Guardamos directamente
        User savedUser = userRepository.save(user);

        // Retornamos el DTO del usuario guardado
        return modelMapper.map(savedUser, UserDTO.class);
    }
}

