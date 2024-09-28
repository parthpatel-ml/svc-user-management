package com.insta.svc_user_management.controller;

import com.insta.svc_user_management.base.IBaseController;
import com.insta.svc_user_management.dto.UserDetailDTO;
import com.insta.svc_user_management.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegistrationController implements IBaseController {

    @Autowired
    IUserService userService;

    @GetMapping("/healthCheck")
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok("UserRegistrationController is UP");
    }

    @GetMapping("/root")
    public ResponseEntity<String> root(@RequestParam String message) {
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDetailDTO> createUser(@Valid @RequestBody UserDetailDTO userDetailDTO) {
        return new ResponseEntity<>(userService.createUser(userDetailDTO), HttpStatus.CREATED);
    }
}
