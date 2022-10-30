package com.lji.portal.repository;

import com.lji.portal.model.entity.User;
import com.lji.portal.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findUserByUserEmailAndPhoneNumberAndDeleteYn(String userEmail, String phoneNumber, boolean deleteYn);
}
