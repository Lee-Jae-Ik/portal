package com.lji.portal.repository.custom.impl;

import com.lji.portal.exception.ApiRuntimeException;
import com.lji.portal.model.entity.QUser;
import com.lji.portal.model.entity.User;
import com.lji.portal.model.response.ApiResult;
import com.lji.portal.repository.custom.UserRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

/**
 * UserRepositoryCustomImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/30
 */
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public User findUserByUserId(Long userID) {

        if (ObjectUtils.isEmpty(userID)) throw new ApiRuntimeException(ApiResult.SERVER_ERROR);

        return jpaQueryFactory.select(QUser.user)
                .from(QUser.user)
                .where(QUser.user.deleteYn.isFalse(), QUser.user.userId.eq(userID))
                .fetchOne();
    }
}
