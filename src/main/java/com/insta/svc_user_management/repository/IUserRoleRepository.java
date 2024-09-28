package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "select * from user_role where user_role =:role", nativeQuery = true)
    Optional<UserRole> findByUserRole(@Param("role") String role);
}
