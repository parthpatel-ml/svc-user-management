package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.model.Gender;
import com.insta.svc_user_management.model.UserAccountType;
import com.insta.svc_user_management.model.UserRole;
import com.insta.svc_user_management.repository.IGenderRepository;
import com.insta.svc_user_management.repository.IUserAccountTypeRepository;
import com.insta.svc_user_management.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.Arrays;

@Component
public class EnumDataLoader {

    @Autowired
    private IGenderRepository iGenderRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Autowired
    private IUserAccountTypeRepository iUserAccountTypeRepository;

    @PostConstruct
    private void loadGenderEnumIntoDatabase() {
        if (iGenderRepository.count() == 0) {
            Arrays.stream(GenderEnum.values()).forEach(gender -> iGenderRepository
                    .save(Gender.builder().genderEnum(gender).build()));
        }
    }

    @PostConstruct
    private void loadUserRoleEnumIntoDatabase() {
        if (iUserRoleRepository.count() == 0) {
            Arrays.stream(UserRoleEnum.values()).forEach(userRole -> iUserRoleRepository
                    .save(UserRole.builder().userRoleEnum(userRole).build())
            );
        }
    }

    @PostConstruct
    private void loadUserAccountTypeEnumIntoDatabase() {
        if (iUserAccountTypeRepository.count() == 0) {
            Arrays.stream(UserAccountTypeEnum.values())
                    .forEach(userAccountType -> iUserAccountTypeRepository
                            .save(UserAccountType.builder().userAccountTypeEnum(userAccountType).build())
                    );
        }

    }
}
