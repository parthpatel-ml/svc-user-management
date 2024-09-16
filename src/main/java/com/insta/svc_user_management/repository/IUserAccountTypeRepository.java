package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.model.UserAccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAccountTypeRepository extends JpaRepository<UserAccountType, Long> {
}
