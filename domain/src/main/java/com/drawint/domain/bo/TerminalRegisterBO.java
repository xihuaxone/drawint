package com.drawint.domain.bo;

import com.drawint.domain.enums.TerminalActionCodeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TerminalRegisterBO {

    private String topic;

    private String name;

    private List<ActionRegisterBO> actionList;

    {
        actionList = new ArrayList<>(5);
    }

    public boolean hasConnCheckAction() {
        for (ActionRegisterBO action : actionList) {
            if (action.getCode().equals(TerminalActionCodeEnum.CONN_CHECK.getCode())) {
                return true;
            }
        }
        return false;
    }
}
