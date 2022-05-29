package com.drawint.domain.bo;

import com.drawint.domain.entity.TerminalMQTT;
import lombok.Data;

import java.util.List;

@Data
public class TerminalBO {
    private Long id;

    private String topic;

    private String name;

    private List<TerminalActionBO> actionList;

    public TerminalBO(TerminalMQTT terminalMQTT) {
        this.id = terminalMQTT.getId();
        this.topic = terminalMQTT.getTopic();
        this.name = terminalMQTT.getName();
    }

    public void addAction(TerminalActionBO terminalActionBO) {
        actionList.add(terminalActionBO);
    }

    public void addAction(List<TerminalActionBO> terminalActionBOList) {
        actionList.addAll(terminalActionBOList);
    }
}
