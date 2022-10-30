package com.lji.portal.service.impl;

import com.lji.portal.exception.ApiRuntimeException;
import com.lji.portal.model.dto.UserFindDto;
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
import java.time.ZoneId;
import java.util.Optional;

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

        long today = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (!StringUtils.hasText(userInsertDto.getUserName())) userInsertDto.setUserName("Guest" + today);

        if (validationUserDuplicationCheck(userInsertDto))
            throw new ApiRuntimeException(ApiResult.DUPLICATION_USER, "이미 존재 하는 아이디 입니다.");

        userRepository.save(User.builder()
                        .userEmail(userInsertDto.getUserEmail())
                        .phoneNumber(userInsertDto.getPhoneNumber())
                        .userPassword(userInsertDto.getUserPassword())
                        .userName(userInsertDto.getUserName())
                        .userCreatedDate(LocalDateTime.now())
                .build());
        return userInsertDto;
    }

    @Override
    public UserFindDto selectOneUser(Long userId) {

        User findUser = Optional.ofNullable(userRepository.findUserByUserId(userId))
                .orElseThrow(() -> new ApiRuntimeException(ApiResult.NO_DATA, "존재하지 않은 유저 입니다."));

        return UserFindDto.builder()
                .userEmail(findUser.getUserEmail())
                .userName(findUser.getUserName())
                .phoneNumber(findUser.getPhoneNumber())
                .build();
    }

    private boolean validationUserDuplicationCheck(UserInsertDto userInsertDto) {
        return userRepository.findUserByUserEmailAndPhoneNumberAndDeleteYn(userInsertDto.getUserEmail(), userInsertDto.getPhoneNumber(), false).isPresent();
    }
}
