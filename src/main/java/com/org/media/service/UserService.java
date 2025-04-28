package com.org.media.service;

import com.org.media.entity.UserInfo;
import com.org.media.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to the system";
    }

    public UserInfo findUserById(int id){
        return userInfoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found" + id));
    }

}
