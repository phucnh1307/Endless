package com.java5.assignment.services;

import com.java5.assignment.dto.UserInfoDto;
import com.java5.assignment.entities.User;
import com.java5.assignment.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserInfoDto getUserInfo(String key) {
        User user = userRepository.findByUserKey(key);
        UserInfoDto userDto = new UserInfoDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }
}