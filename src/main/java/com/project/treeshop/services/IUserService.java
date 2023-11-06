package com.project.treeshop.services;

import com.project.treeshop.dto.UpdateUserDTO;
import com.project.treeshop.dto.UserDTO;
import com.project.treeshop.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String phoneNumber, String password, Long roleId) throws Exception;

    User getUserDetailsFromToken(String token) throws Exception;

    User getUserById (long id);

//    User updateUser(Long userId, UpdateUserDTO updatedUserDTO) throws Exception;
}
