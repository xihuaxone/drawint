package com.drawint.domain.bo;

import com.drawint.domain.entity.TerminalMQTT;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TerminalBO {
    private Long id;

    private String topic;

    private String name;

    private List<TerminalActionBO> actionList;

    {
        actionList = new ArrayList<>(5);
    }

    public TerminalBO(TerminalMQTT terminalMQTT) {
        this.id = terminalMQTT.getId();
        this.topic = terminalMQTT.getTopic();
        this.name = terminalMQTT.getName();
    }

    public void addAction(TerminalActionBO terminalActionBO) {
        actionList.add(terminalActionBO);
    }

    public void addAction(List<TerminalActionBO> terminalActionBOList) {
        if (terminalActionBOList == null || terminalActionBOList.isEmpty()) {
            return;
        }
        actionList.addAll(terminalActionBOList);
    }
}
