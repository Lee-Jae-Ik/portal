package com.lji.portal.controller;

import com.lji.portal.controller.base.extend.BaseController;
import com.lji.portal.model.dto.UserInsertDto;
import com.lji.portal.model.response.result.ApiResponse;
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
@RequestMapping(value = "/v1")
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ApiResponse> insertUser(@RequestBody @Valid UserInsertDto userInsertDto) {
        return callRestApi(userService.insertUser(userInsertDto));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> selectOneUser(@RequestParam Long userId) {
        return callRestApi(userService.selectOneUser(userId));
    }

    @GetMapping("/user/all")
    public ResponseEntity<ApiResponse> findAllUser() {
        return callRestApi(userService.selectListUser());
    }
}
