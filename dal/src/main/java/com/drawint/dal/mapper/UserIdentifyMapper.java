package com.drawint.dal.mapper;

import com.drawint.domain.entity.UserIdentify;
import com.drawint.domain.entity.UserIdentifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserIdentifyMapper {
    long countByExample(UserIdentifyExample example);

    int deleteByExample(UserIdentifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserIdentify record);

    int insertSelective(UserIdentify record);

    List<UserIdentify> selectByExample(UserIdentifyExample example);

    UserIdentify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserIdentify record, @Param("example") UserIdentifyExample example);

    int updateByExample(@Param("record") UserIdentify record, @Param("example") UserIdentifyExample example);

    int updateByPrimaryKeySelective(UserIdentify record);

    int updateByPrimaryKey(UserIdentify record);
}