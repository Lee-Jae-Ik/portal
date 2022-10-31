package com.lji.portal.controller;

import com.lji.portal.controller.base.extend.BaseController;
import com.lji.portal.model.dto.UserInsertDto;
import com.lji.portal.model.response.result.ApiListObjectResponse;
import com.lji.portal.model.response.result.ApiUserEntityResponse;
import com.lji.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * UserController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ApiUserEntityResponse> insertUser(@RequestBody @Valid UserInsertDto userInsertDto) {
        return callRestApiUserDtoResult(userService.insertUser(userInsertDto));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiUserEntityResponse> selectOneUser(@RequestParam Long userId) {
        return callRestApiUserDtoResult(userService.selectOneUser(userId));
    }

    @GetMapping("/user/all")
    public ResponseEntity<ApiListObjectResponse> findAllUser() {
        return callRestApiListObjectResult(userService.selectListUser());
    }
}
