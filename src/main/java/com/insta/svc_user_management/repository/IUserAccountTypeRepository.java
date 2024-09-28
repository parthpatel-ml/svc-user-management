package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.model.UserAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserAccountTypeRepository extends JpaRepository<UserAccountType, Long> {

    @Query(value = "select * from user_account_type where user_account_type=:account_type", nativeQuery = true)
    Optional<UserAccountType> findByUserAccountType(@Param("account_type") String accountType);
}
