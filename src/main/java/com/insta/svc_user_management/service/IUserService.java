package com.insta.svc_user_management.service;

import com.insta.svc_user_management.base.IBaseService;
import com.insta.svc_user_management.dto.UserDetailDTO;


public interface IUserService extends IBaseService {

    public UserDetailDTO createUser(UserDetailDTO userDetailDTO);

}
