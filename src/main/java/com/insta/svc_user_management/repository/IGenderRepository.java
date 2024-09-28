package com.insta.svc_user_management.repository;

import com.insta.svc_user_management.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IGenderRepository extends JpaRepository<Gender, Long> {
    @Query(value = "select * from gender where gender=:name", nativeQuery = true)
    Optional<Gender> findByGender(@Param("name") String name);
}
