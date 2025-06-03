package com.unac.salescarpremium.services;

import com.unac.salescarpremium.dtos.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO save(UserDTO userDTO);
}