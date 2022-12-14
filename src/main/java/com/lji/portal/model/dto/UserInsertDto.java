package com.lji.portal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * UserInsertDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserInsertDto extends UserBasic {

    @NotNull(message = "User's password must be not null !!")
    private String userPassword;

    @NotNull(message = "User's phone number must be not null !!")
    private String phoneNumber;

    private String userName;
}
