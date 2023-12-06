package com.vidracariaCampos.service;

import com.vidracariaCampos.dto.UserDTO;
import com.vidracariaCampos.entity.Role;
import com.vidracariaCampos.entity.User;
import com.vidracariaCampos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(email);
        return user != null ? user: null;
    }

    public UserDTO getUserByEmail(String email) {
        User u =  (User) userRepository.findByEmail(email);
        return u != null ? UserConverter.convertToUserDTO(u): null;
    }

    public List<UserDTO> getAllUsers() {
        List<User> u = userRepository.findAll();
        return u != null ? UserConverter.convertToUserDTOList(u): null;
    }

    public void saveUser(UserDTO userDTO) {

        User newUser = UserConverter.convertToUser(userDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    public void deleteUser(String email) {
        if(getUserByEmail(email) == null){
            throw new RuntimeException("Email not found");
        }
        userRepository.deleteById(email);
    }

    public void update(UserDTO userDTO) {
        User user =  userRepository.findByNameOrEmail(userDTO.name(), userDTO.email());
        if (userDTO.name() != null) { user.setName(userDTO.name());}
        if (userDTO.password() != null) {user.setPassword(passwordEncoder.encode(userDTO.password()));}
        if (userDTO.role() != null) {user.setRole(Role.valueOf(userDTO.role()));}
        userRepository.save(user);
    }
}