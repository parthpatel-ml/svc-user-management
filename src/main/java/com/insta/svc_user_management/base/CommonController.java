package com.insta.svc_user_management.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CommonController implements IBaseController {
    @GetMapping
    public ResponseEntity<String> root() {
        return new ResponseEntity<>("dispatcherServlet Up", HttpStatus.OK);
    }
}
