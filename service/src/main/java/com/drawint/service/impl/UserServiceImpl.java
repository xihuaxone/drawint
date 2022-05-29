package com.drawint.service.impl;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.common.utils.RSAUtil;
import com.drawint.common.utils.SHA256Util;
import com.drawint.dal.mapper.UserIdentifyMapper;
import com.drawint.dal.mapper.UserMapper;
import com.drawint.domain.bo.*;
import com.drawint.domain.entity.User;
import com.drawint.domain.entity.UserExample;
import com.drawint.domain.entity.UserIdentify;
import com.drawint.domain.entity.UserIdentifyExample;
import com.drawint.service.UserService;
import com.drawint.domain.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Repository
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Autowired
    UserIdentifyMapper userIdentifyMapper;

    @Override
    public List<UserBO> list() {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(false);
        return UserConverter.userConverter.user2UserBO(userMapper.selectByExample(example));
    }

    @Override
    public UserBO get(Long uid) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(uid).andIsDeletedEqualTo(false);
        List<User> users = userMapper.selectByExample(example);
        return users.isEmpty() ? null : UserConverter.userConverter.user2UserBO(users.get(0));
    }

    @Override
    public List<UserBO> getByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(false);
        criteria.andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);

        return UserConverter.userConverter.user2UserBO(users);
    }

    @Override
    public void add(UserBO userBO) {
        assertUserNotExists(userBO.getName());
        userBO.setId(null);
        User user = UserConverter.userConverter.userBO2User(userBO);
        userMapper.insertSelective(user);
    }

    @Override
    public void update(UserBO userBO) throws BizException {
        User user = UserConverter.userConverter.userBO2User(userBO);
        assertUserNotExists(user.getName());
        if (Objects.isNull(user.getId())) {
            throw new BizException(BizExceptionType.PARAM_MISSING, "user.id can not be null");
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(Long uid) {
        User user = new User();
        user.setId(uid);
        user.setIsDeleted(true);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void assertUserNotExists(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(false);
        criteria.andNameEqualTo(name);
        if (userMapper.countByExample(example) > 0) {
            throw new BizException(BizExceptionType.USER_ALREADY_EXISTS,
                    String.format("user.name %s already exists", name));
        }
    }

    @Override
    public void register(UserRegisterBO userRegisterBO) {
        add(UserConverter.userConverter.userRegisterBO2UserBO(userRegisterBO));
        Long uid = getByName(userRegisterBO.getName()).get(0).getId();
        UserIdentify identify = UserConverter.userConverter.userRegisterBO2UserIdentify(userRegisterBO);
        identify.setUid(uid);
        identify.setPassword(RSAUtil.decryptByPrivateKey(userRegisterBO.getPassword()));
        userIdentifyMapper.insertSelective(identify);
    }

    @Override
    public boolean identify(UserIdentifyBO identifyBO) {
        List<UserIdentify> userIdentifyList = getUserIdentify(identifyBO.getAccount(),
                identifyBO.getAccountType(), false);
        if (userIdentifyList.isEmpty()) {
            throw new BizException(BizExceptionType.USER_ACCOUNT_NOT_EXISTS);
        }
        UserBO userBO = get(userIdentifyList.get(0).getUid());
        if (userBO == null) {
            throw new BizException(BizExceptionType.USER_NOT_EXISTS);
        }
        return RSAUtil.decryptByPrivateKey(identifyBO.getPassword()).equals(userIdentifyList.get(0).getPassword());
    }

    @Override
    public void updatePassword(UserIdentifyUpdateBO updateBO) {
        List<UserIdentify> userIdentifyList = getUserIdentify(updateBO.getAccount(),
                updateBO.getAccountType(), false);
        if (userIdentifyList.isEmpty()) {
            throw new BizException(BizExceptionType.USER_ACCOUNT_NOT_EXISTS);
        }
        UserIdentify userIdentify = userIdentifyList.get(0);
        userIdentify.setIsDeleted(true);

        UserIdentify newUserIdentify = new UserIdentify();
        newUserIdentify.setAccount(userIdentify.getAccount());
        newUserIdentify.setAccountType(userIdentify.getAccountType());
        newUserIdentify.setUid(userIdentify.getUid());
        newUserIdentify.setPassword(SHA256Util.encrypt(updateBO.getNewPassword()));

        userIdentifyMapper.insertSelective(newUserIdentify);
        userIdentifyMapper.updateByPrimaryKeySelective(userIdentify);
    }

    @Override
    public Long getUidByAccount(String account) {
        UserIdentifyExample example = new UserIdentifyExample();
        example.createCriteria()
                .andAccountEqualTo(account)
                .andIsDeletedEqualTo(false);
        List<UserIdentify> userIdentifies = userIdentifyMapper.selectByExample(example);
        return userIdentifies.size() <= 0 ? null : userIdentifies.get(0).getUid();
    }

    private List<UserIdentify> getUserIdentify(String account, String accountType, boolean isDeleted) {
        UserIdentifyExample example = new UserIdentifyExample();
        UserIdentifyExample.Criteria criteria = example.createCriteria();
        criteria.andAccountTypeEqualTo(accountType)
                .andAccountEqualTo(account)
                .andIsDeletedEqualTo(isDeleted);
        return userIdentifyMapper.selectByExample(example);
    }
}
