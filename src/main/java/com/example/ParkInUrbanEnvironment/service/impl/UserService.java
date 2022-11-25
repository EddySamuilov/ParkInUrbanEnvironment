package com.example.carpark.service.impl;

import com.example.carpark.dto.RegisterDTO;
import com.example.carpark.dto.UserDTO;
import com.example.carpark.entity.RoleEntity;
import com.example.carpark.entity.RoleEnumType;
import com.example.carpark.entity.User;
import com.example.carpark.repository.UserRepository;
import com.example.carpark.repository.UserRoleRepository;
import com.example.carpark.service.BaseService;
import com.example.carpark.service.CarParkUserDetailsService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserService implements BaseService<UserDTO> {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CarParkUserDetailsService carParkService;

    @Override
    public Collection<UserDTO> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
    }



    @Override
    public UserDTO create(UserDTO model) {
        User user = modelMapper.map(model, User.class);
        user.setCreated(Instant.now());
        user.setModified(Instant.now());
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userRepository.save(user);
        return model;
    }

    @Override
    public UserDTO findById(String id) throws NotFoundException {
        return this.userRepository.findById(id)
                .map(u -> modelMapper.map(u, UserDTO.class))
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    public boolean remove(String id) {
        return userRepository.findAll().remove(id);
    }

    @Override
    public UserDTO update(String id, UserDTO model) {
        return null;
    }

    @Override
    public UserDTO getByName(String name) throws NotFoundException {
        return this.userRepository.findByUsername(name)
                .map(u -> modelMapper.map(u, UserDTO.class))
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    public boolean isUserExists(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        return user != null;
    }

    public void registerUser(RegisterDTO registerDTO) {

        RoleEntity userRole = userRoleRepository.findByRole(RoleEnumType.USER).orElse(null);

        User newUser = new User();

        newUser.setUsername(registerDTO.getUsername());
        newUser.setFirstName(registerDTO.getFirstName());
        newUser.setLastName(registerDTO.getLastName());
        newUser.setEmail(registerDTO.getEmail());
        newUser.setAge(registerDTO.getAge());
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        newUser.setRoles(List.of(userRole));
        newUser.setCreated(Instant.now());
        newUser.setModified(Instant.now());

        newUser = userRepository.save(newUser);

        UserDetails principal = carParkService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }
    public User createV1(User model) {
        model.setCreated(Instant.now());
        model.setModified(Instant.now());
        String encode = passwordEncoder.encode(model.getPassword());
        model.setPassword(encode);
        return this.userRepository.save(model);
    }
    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }
}

