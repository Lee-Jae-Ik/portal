package com.lji.portal.service;

import com.lji.portal.model.dto.UserInsertDto;

/**
 * UserService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public interface UserService {

    UserInsertDto insertUser(UserInsertDto userInsertDto);
}
