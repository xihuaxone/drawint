package com.drawint.service.impl;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.dal.relay.RelayTcpMaster;
import com.drawint.service.RelayDeviceService;
import com.drawint.service.annotation.RelayActionLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RelayDeviceServiceImpl implements RelayDeviceService {
    private final int slaveId = 1;

    @Autowired
    RelayTcpMaster relayTcpMaster;

    @Override
    public boolean portIsOn(int switchPort) {
        try {
            return relayTcpMaster.getStatus(slaveId, switchPort);
        } catch (Exception e) {
            throw new BizException(e);
        }
    }

    @RelayActionLock
    @Override
    public boolean switchOff(int switchPort) {
        return executeSend(switchPort, false);
    }

    @RelayActionLock
    @Override
    public boolean switchOn(int switchPort) {
        return executeSend(switchPort, true);
    }

    @RelayActionLock
    @Override
    public boolean switchStatus(int switchPort) {
        boolean status;
        try {
            status = relayTcpMaster.getStatus(slaveId, switchPort);
        } catch (Exception e) {
            throw new BizException(e);
        }
        return executeSend(switchPort, !status);
    }

    @RelayActionLock
    @Override
    public boolean switchOnAndOff(int switchPort) {
        boolean successOn = switchOn(switchPort);
        switchOff(switchPort);
        if (portIsOn(switchPort)) {
            boolean successOffAgain = switchOff(switchPort);
            if (!successOffAgain) {
                throw new BizException(BizExceptionType.DOOR_CLOSE_FAILED);
            }
        }
        if (!successOn) {
            throw new BizException(BizExceptionType.DOOR_OPEN_FAILED);
        }
        return true;
    }

    private boolean executeSend(int switchPort, boolean signal) {
        try {
            return relayTcpMaster.sendSignal(slaveId, switchPort, signal);
        } catch (Exception e) {
            log.warn("slave {} switchPort {} error: {}", slaveId, switchPort, e.toString());
            return false;
        }
    }
}
