package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.base.IBaseRepository;
import com.insta.svc_user_management.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserDetail, Long>, IBaseRepository {
    // search about @NonNullApi

}

