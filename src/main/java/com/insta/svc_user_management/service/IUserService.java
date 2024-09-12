package com.insta.svc_user_management.service;

import com.insta.svc_user_management.base.IBaseService;
import com.insta.svc_user_management.model.User;



public interface IUserService extends IBaseService {

    public User createUser(User user);

}
