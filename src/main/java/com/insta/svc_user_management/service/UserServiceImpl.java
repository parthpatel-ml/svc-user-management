package com.insta.svc_user_management.service;

import com.insta.svc_user_management.model.UserDetail;
import com.insta.svc_user_management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public UserDetail createUser(UserDetail userDetail) {
        return iUserRepository.save(userDetail);
    }
}
