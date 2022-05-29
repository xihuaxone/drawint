package com.drawint.dal.mapper;

import com.drawint.domain.entity.TerminalAction;
import com.drawint.domain.entity.TerminalActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TerminalActionMapper {
    long countByExample(TerminalActionExample example);

    int deleteByExample(TerminalActionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TerminalAction record);

    int insertSelective(TerminalAction record);

    List<TerminalAction> selectByExample(TerminalActionExample example);

    TerminalAction selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TerminalAction record, @Param("example") TerminalActionExample example);

    int updateByExample(@Param("record") TerminalAction record, @Param("example") TerminalActionExample example);

    int updateByPrimaryKeySelective(TerminalAction record);

    int updateByPrimaryKey(TerminalAction record);
}