package com.lji.portal.repository;

import com.lji.portal.model.entity.User;
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
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserEmailAAndPhoneNumberAndDeleteYn(String userEmail, String phoneNumber, boolean deleteYn);
}
