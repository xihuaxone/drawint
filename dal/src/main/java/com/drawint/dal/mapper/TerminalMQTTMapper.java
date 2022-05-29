package com.drawint.dal.mapper;

import com.drawint.domain.entity.TerminalMQTT;
import com.drawint.domain.entity.TerminalMQTTExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TerminalMQTTMapper {
    long countByExample(TerminalMQTTExample example);

    int deleteByExample(TerminalMQTTExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TerminalMQTT record);

    int insertSelective(TerminalMQTT record);

    List<TerminalMQTT> selectByExample(TerminalMQTTExample example);

    TerminalMQTT selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TerminalMQTT record, @Param("example") TerminalMQTTExample example);

    int updateByExample(@Param("record") TerminalMQTT record, @Param("example") TerminalMQTTExample example);

    int updateByPrimaryKeySelective(TerminalMQTT record);

    int updateByPrimaryKey(TerminalMQTT record);
}