package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.base.IBaseRepository;
import com.insta.svc_user_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends IBaseRepository, JpaRepository<User, Long> {

}
