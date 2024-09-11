package com.insta.svc_user_management.controller;

import com.insta.svc_user_management.base.IBaseController;
import com.insta.svc_user_management.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegistrationController implements IBaseController {


    @GetMapping("/greeting")
    public String greeting() {
        return "UserRegistrationController";
    }

    @GetMapping("/root")
    public String root(@RequestParam String message) {
        return message;
    }
}
