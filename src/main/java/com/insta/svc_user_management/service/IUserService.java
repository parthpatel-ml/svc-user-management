package com.insta.svc_user_management.service;

import com.insta.svc_user_management.base.IBaseService;
import com.insta.svc_user_management.model.UserDetail;


public interface IUserService extends IBaseService {

    public UserDetail createUser(UserDetail userDetail);

}
