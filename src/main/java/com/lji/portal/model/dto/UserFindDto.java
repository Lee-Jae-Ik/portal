package com.lji.portal.model.dto;

import lombok.*;

/**
 * UserFindDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/30
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserFindDto extends UserBasic{

    private String userEmail;
    private String userName;
    private String phoneNumber;
}
