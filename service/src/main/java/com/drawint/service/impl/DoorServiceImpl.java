package com.drawint.service.impl;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.domain.bo.DoorActionBO;
import com.drawint.domain.enums.DoorActionEnum;
import com.drawint.service.DoorService;
import com.drawint.service.RelayDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoorServiceImpl implements DoorService {
    @Autowired
    RelayDeviceService relayDeviceService;

    @Override
    public void doAction(DoorActionBO actionBO) {
        if (actionBO.getAction().equals(DoorActionEnum.OPEN.getCode())) {
            open(actionBO.getSwitchPort());
        } else if (actionBO.getAction().equals(DoorActionEnum.CLOSE.getCode())) {
            close(actionBO.getSwitchPort());
        } else if (actionBO.getAction().equals(DoorActionEnum.RESET.getCode())) {
            reset(actionBO.getSwitchPort());
        } else {
            throw new RuntimeException("unknown action");
        }
    }

    public void open(Integer switchPort) {

    }

    public void close(Integer switchPort) {

    }

    public void reset(Integer switchPort) {
        boolean success = relayDeviceService.switchOnAndOff(switchPort);
        if (!success) {
            throw new BizException(BizExceptionType.DOOR_OPEN_FAILED);
        }
    }
}
