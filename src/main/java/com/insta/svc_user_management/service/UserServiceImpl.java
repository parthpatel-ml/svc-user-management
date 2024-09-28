package com.insta.svc_user_management.service;

import com.insta.svc_user_management.dto.UserDetailDTO;
import com.insta.svc_user_management.manager.UserDetailsManager;
import com.insta.svc_user_management.model.UserDetail;
import com.insta.svc_user_management.repository.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    @Qualifier("userDetailsManager")
    UserDetailsManager userDetailsManager;

    @Override
    public UserDetailDTO createUser(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = userDetailsManager.convertDTOtoModel(userDetailDTO);
        UserDetail save = save(userDetail);
        return userDetailsManager.convertModelToDTO(save);
    }

    public UserDetail save(UserDetail model) {
        return iUserRepository.save(model);
    }
}
