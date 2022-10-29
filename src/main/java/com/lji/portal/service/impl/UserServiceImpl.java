package com.lji.portal.service.impl;

import com.lji.portal.exception.ApiRuntimeException;
import com.lji.portal.model.dto.UserInsertDto;
import com.lji.portal.model.entity.User;
import com.lji.portal.model.response.ApiResult;
import com.lji.portal.repository.UserRepository;
import com.lji.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * UserServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserInsertDto insertUser(UserInsertDto userInsertDto) {

        if (StringUtils.hasText(userInsertDto.getUserName())) userInsertDto.setUserName("Guest" + Timestamp.valueOf(LocalDateTime.now()));

        if (validationUserDuplicationCheck(userInsertDto)) throw new ApiRuntimeException(ApiResult.DUPLICATION_USER, "이미 존재 하는 아이디 입니다.");

        userRepository.save(User.builder()
                        .userEmail(userInsertDto.getUserEmail())
                        .phoneNumber(userInsertDto.getPhoneNumber())
                        .userPassword(userInsertDto.getUserPassword())
                        .userName(userInsertDto.getUserName())
                .build());
        return userInsertDto;
    }

    private boolean validationUserDuplicationCheck(UserInsertDto userInsertDto) {
        return userRepository.findUserByUserEmailAAndPhoneNumberAndDeleteYn(userInsertDto.getUserEmail(), userInsertDto.getPhoneNumber(), false).isPresent();
    }
}
