package com.drawint.service.util;

import com.drawint.domain.bo.TerminalBO;

public interface TerminalActionUtil {
    void doAction(TerminalBO terminalBO, String action);

    boolean checkAction(TerminalBO terminalBO, String action);
}
