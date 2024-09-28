package com.insta.svc_user_management.manager;

import com.insta.svc_user_management.base.IBaseManager;
import com.insta.svc_user_management.dto.UserDetailDTO;
import com.insta.svc_user_management.enums.GenderEnum;
import com.insta.svc_user_management.enums.UserAccountTypeEnum;
import com.insta.svc_user_management.enums.UserRoleEnum;
import com.insta.svc_user_management.exception.InvalidEnumDataException;
import com.insta.svc_user_management.model.Gender;
import com.insta.svc_user_management.model.UserAccountType;
import com.insta.svc_user_management.model.UserDetail;
import com.insta.svc_user_management.model.UserRole;
import com.insta.svc_user_management.repository.IGenderRepository;
import com.insta.svc_user_management.repository.IUserAccountTypeRepository;
import com.insta.svc_user_management.repository.IUserRoleRepository;
import jakarta.validation.Valid;
import org.hibernate.jpa.internal.util.PessimisticNumberParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;

@Component("userDetailsManager")
public class UserDetailsManager implements IBaseManager {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IGenderRepository iGenderRepository;

    @Autowired
    IUserRoleRepository iUserRoleRepository;

    @Autowired
    IUserAccountTypeRepository iUserAccountTypeRepository;

    public UserDetail convertDTOtoModel(UserDetailDTO dto) {
        Optional<Gender> byGender = Optional.empty();
        Optional<UserAccountType> byAccountType = Optional.empty();
        Optional<UserRole> byUserRole = Optional.empty();

        boolean isGender = Arrays.stream(GenderEnum.values()).anyMatch(enumValue -> enumValue.name().equals(dto.getGender()));
        if (isGender) {
            byGender = iGenderRepository.findByGender(dto.getGender());
        }else {
            throw new InvalidEnumDataException("Gender Enum data is not valid");
        }

        boolean isAccountType = Arrays.stream(UserAccountTypeEnum.values()).anyMatch(enumValue -> enumValue.name().equals(dto.getUserAccountType()));
        if (isAccountType) {
            byAccountType = iUserAccountTypeRepository.findByUserAccountType(dto.getUserAccountType());
        }else{
            throw new InvalidEnumDataException("Account Type Enum data is not valid");
        }

        boolean isUserRole = Arrays.stream(UserRoleEnum.values()).anyMatch(enumValue -> enumValue.name().equals(dto.getUserRole()));
        if (isUserRole) {
            byUserRole = iUserRoleRepository.findByUserRole(dto.getUserRole());
        }else{
            throw new InvalidEnumDataException("User Role Enum Data is not valid");
        }

        UserDetail userDetail = modelMapper.map(dto, UserDetail.class);
        byGender.ifPresent(userDetail::setGender);
        byAccountType.ifPresentOrElse(userDetail::setUserAccountType, null);
        if (byUserRole.isPresent()) {
            userDetail.setUserRole(byUserRole.get());
        }
        return userDetail;
    }

    public UserDetailDTO convertModelToDTO(@Valid UserDetail userDetail) {
        UserDetailDTO dto = modelMapper.map(userDetail, UserDetailDTO.class);
        dto.setGender(userDetail.getGender().getGenderEnum().name());
        dto.setUserRole(userDetail.getUserRole().getUserRoleEnum().name());
        dto.setUserAccountType(userDetail.getUserAccountType().getUserAccountTypeEnum().name());
        return dto;
    }
}
