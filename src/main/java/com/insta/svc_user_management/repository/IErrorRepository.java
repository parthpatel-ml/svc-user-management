package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.base.IBaseRepository;
import com.insta.svc_user_management.model.Errors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IErrorRepository extends JpaRepository<Errors, Long>, IBaseRepository {
}
