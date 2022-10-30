package com.lji.portal.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * UserBasic
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/30
 */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserBasic {

    @NotNull(message = "User's email must be not null !!")
    private String userEmail;

    @NotNull(message = "User's name must be not null !!")
    private String userName;
}
