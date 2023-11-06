package com.project.treeshop.services;


import com.project.treeshop.component.JwtTokenUtils;
import com.project.treeshop.dto.UserDTO;
import com.project.treeshop.models.Role;
import com.project.treeshop.models.User;
import com.project.treeshop.repositories.RoleRepository;
import com.project.treeshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override

    public User createUser(UserDTO userDTO)  {
        //register user
        // Kiểm tra xem số name đã tồn tại hay chưa
        if(userRepository.existsByName(userDTO.getName())) {
            throw new DataIntegrityViolationException("Name already exists");
        }
        Role role =roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException(
                      "ROLE_DOES_NOT_EXISTS"));
        if(role.getName().toUpperCase().equals(Role.ADMIN)) {
            throw new RuntimeException("You cannot register an admin account");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        //convert from userDTO => user
        User newUser = User.builder()
                .name(userDTO.getName())
                .password(hashedPassword)
                .isActive(true)
                .build();

        newUser.setRole(role);

        return userRepository.save(newUser);
    }

    @Override
    public String login(
            String name,
            String password,
            Long roleId
    ) throws Exception {
        Optional<User> optionalUser = userRepository.findByName(name);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("WRONG_PHONE_PASSWORD");
        }
        //return optionalUser.get();//muốn trả JWT token ?
        User existingUser = optionalUser.get();
        //check password
            if(!passwordEncoder.matches(password, existingUser.getPassword())) {
                throw new RuntimeException("WRONG_PHONE_PASSWORD");
            }

//        Optional<Role> optionalRole = roleRepository.findById(roleId);
//        if(optionalRole.isEmpty() || !roleId.equals(existingUser.getRole().getId())) {
//            throw new RuntimeException("ROLE_DOES_NOT_EXISTS");
//        }
        if(!optionalUser.get().isActive()) {
            throw new RuntimeException("USER_IS_LOCKED");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                name, password,
                existingUser.getAuthorities()
        );

        //authenticate with Java Spring security
        authenticationManager.authenticate(authenticationToken);

        String token = jwtTokenUtil.generateToken(existingUser);
        System.out.println("Generated token: " + token);

        return token;
    }

    @Override
    public User getUserDetailsFromToken(String token) throws Exception {
            if(jwtTokenUtil.isTokenExpired(token)) {
                throw new Exception("Token is expired");
            }
            String name = jwtTokenUtil.extractPhoneNumber(token);
            Optional<User> user = userRepository.findByName(name);

            if (user.isPresent()) {
                return user.get();
            } else {
                throw new Exception("User not found");
            }
        }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Can not find user id"));

    }


}
