package com.drawint.dal.mapper;

import com.drawint.domain.entity.UserTerminalActionPermission;
import com.drawint.domain.entity.UserTerminalActionPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTerminalActionPermissionMapper {
    long countByExample(UserTerminalActionPermissionExample example);

    int deleteByExample(UserTerminalActionPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserTerminalActionPermission record);

    int insertSelective(UserTerminalActionPermission record);

    List<UserTerminalActionPermission> selectByExample(UserTerminalActionPermissionExample example);

    UserTerminalActionPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserTerminalActionPermission record, @Param("example") UserTerminalActionPermissionExample example);

    int updateByExample(@Param("record") UserTerminalActionPermission record, @Param("example") UserTerminalActionPermissionExample example);

    int updateByPrimaryKeySelective(UserTerminalActionPermission record);

    int updateByPrimaryKey(UserTerminalActionPermission record);
}