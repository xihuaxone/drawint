package com.drawint.service;

import com.drawint.common.exception.BizException;
import com.drawint.domain.bo.*;

import java.util.List;


public interface UserService {
    List<UserBO> list();

    UserBO get(Long uid);

    List<UserBO> getByName(String name);

    void add(UserBO userBO);

    void update(UserBO userBO) throws BizException;

    void delete(Long uid);

    void assertUserNotExists(String name);

    void register(UserRegisterBO userRegisterBO);

    boolean identify(UserIdentifyBO identifyBO);

    void updatePassword(UserIdentifyUpdateBO updateBO);

    Long getUidByAccount(String account);
}
