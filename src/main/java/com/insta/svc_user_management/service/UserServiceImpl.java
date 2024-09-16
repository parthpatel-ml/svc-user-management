package com.insta.svc_user_management.service;

import com.insta.svc_user_management.dto.UserDetailDTO;
import com.insta.svc_user_management.model.UserDetail;
import com.insta.svc_user_management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public UserDetailDTO createUser(UserDetailDTO userDetailDTO) {
        // first save in Gender table
        // second save in role table
        // third save in userAccountType.
        UserDetail userDetail = new UserDetail();
        UserDetail save = iUserRepository.save(userDetail);
        return null;
    }
}
