package com.org.media.controller;

import com.org.media.cofig.UserInfoUserDetailsService;
import com.org.media.entity.UserInfo;
import com.org.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> findUserById(@PathVariable int id){
         UserInfo u1 = userService.findUserById(id);
         return ResponseEntity.ok(u1);
    }

}
