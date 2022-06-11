package com.drawint.service;

import com.drawint.domain.bo.ActionRegisterBO;
import com.drawint.domain.bo.TerminalBO;
import com.drawint.domain.bo.TerminalRegisterBO;
import com.drawint.domain.entity.UserTerminalActionPermission;

import java.util.List;

public interface TerminalMngService {
    void registerTerminal(TerminalRegisterBO terminalRegisterBO, Long uid);

    void registerAction(Long tmId, List<ActionRegisterBO> actionRegisterBOList, Long uid);

    List<TerminalBO> list(Long uid);

    TerminalBO get(String topic, Long uid);

    TerminalBO get(Long tmId, Long uid);

    UserTerminalActionPermission getActionPermission(Long uid, Long tmId, String actionCode);
}
