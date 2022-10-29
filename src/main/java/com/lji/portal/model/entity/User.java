package com.lji.portal.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * User
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_passwrod", nullable = false)
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "user_created_date", nullable = false)
    private LocalDateTime userCreatedDate;

    @Column(name = "delete_yn")
    private boolean deleteYn = false;
}
