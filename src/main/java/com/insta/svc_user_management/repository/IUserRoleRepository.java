package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
}
