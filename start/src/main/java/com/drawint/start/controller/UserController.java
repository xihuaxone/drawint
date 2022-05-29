package com.drawint.start.controller;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.common.utils.StringUtil;
import com.drawint.domain.bo.UserBO;
import com.drawint.domain.bo.UserIdentifyUpdateBO;
import com.drawint.domain.bo.UserRegisterBO;
import com.drawint.domain.enums.UserAccountTypeEnum;
import com.drawint.domain.validation.UserName;
import com.drawint.service.UserService;
import com.drawint.domain.ApiResult;
import com.drawint.start.annotation.LoginAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@ResponseBody
@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @LoginAuth
    @RequestMapping(path = "/{uid}", method = RequestMethod.GET)
    public ApiResult<UserBO> getUser(@PathVariable @NotNull Long uid) {
        return ApiResult.<UserBO>builder().data(userService.get(uid)).build();
    }

    @LoginAuth
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ApiResult<List<UserBO>> getUserByName(@RequestParam(required = false) @UserName String name) {

        if (StringUtil.isBlank(name)) {
            return ApiResult.<List<UserBO>>builder().data(userService.list()).build();
        }
        return ApiResult.<List<UserBO>>builder().data(userService.getByName(name)).build();
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ApiResult<Void> register(@RequestBody @Valid UserRegisterBO userRegisterBO) {
        if (userRegisterBO.getAccountType().equals(UserAccountTypeEnum.DRAW_INT.getCode())) {
            if (!userRegisterBO.getAccount().equals(userRegisterBO.getName())) {
                throw new BizException(BizExceptionType.DEFAULT_ACCOUNT_TYPE_USERNAME_ILLEGAL);
            }
        }
        userService.register(userRegisterBO);
        return ApiResult.<Void>builder().build();
    }

    @LoginAuth
    @RequestMapping(path = "/{uid}/basic/", method = RequestMethod.PUT)
    public ApiResult<Void> updateBasicInfo(@PathVariable @NotNull Long uid,
                                      @RequestBody @Valid UserBO userBO) {
        userBO.setId(uid);
        userService.update(userBO);
        return ApiResult.<Void>builder().build();
    }

    @LoginAuth
    @RequestMapping(path = "/{uid}/", method = RequestMethod.DELETE)
    public ApiResult<Void> deleteUser(@PathVariable @NotNull Long uid) {
        userService.delete(uid);
        return ApiResult.<Void>builder().build();
    }

    @LoginAuth
    @RequestMapping(path = "/{uid}/password/", method = RequestMethod.PUT)
    public ApiResult<Void> updatePassword(@PathVariable @NotNull Long uid,
                                          @RequestBody UserIdentifyUpdateBO updateBO) {
        updateBO.setUid(uid);
        userService.updatePassword(updateBO);
        return ApiResult.<Void>builder().build();
    }
}
