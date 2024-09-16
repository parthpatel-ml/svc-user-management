package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenderRepository extends JpaRepository<Gender, Long> {
}
