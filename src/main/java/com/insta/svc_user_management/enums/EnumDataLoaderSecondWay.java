/*
package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.model.Errors;
import com.insta.svc_user_management.repository.IErrorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;


public class EnumDataLoaderSecondWay implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private IErrorRepository iErrorRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (iErrorRepository.count() == 0) {
            Arrays.stream(ErrorEnum.values()).forEach(error -> iErrorRepository
                    .save(Errors.builder()
                            .errorEnum(error)
                            .code(error.code)
                            .description(error.description)
                            .build()));
        }
    }


}

*/
