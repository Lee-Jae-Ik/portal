package com.lji.portal.repository.custom;

import com.lji.portal.model.entity.User;

/**
 * UserRepositoryCustom
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/30
 */
public interface UserRepositoryCustom {
    User findUserByUserId(Long userID);
}
