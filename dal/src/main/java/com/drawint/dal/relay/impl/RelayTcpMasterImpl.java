package com.drawint.dal.relay.impl;

import com.alibaba.fastjson.JSON;
import com.drawint.dal.relay.RelayTcpMaster;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RelayTcpMasterImpl implements RelayTcpMaster {
    private static final String HOST = "192.168.1.100";
    private static final int PORT = 10000;
    private static final ModbusFactory modbusFactory;

    static {
        modbusFactory = new ModbusFactory();
    }

    private ModbusMaster createTcpMaster() {
        IpParameters params = new IpParameters();
        params.setHost(HOST);
        params.setPort(PORT);
        ModbusMaster tcpMaster = modbusFactory.createTcpMaster(params, false);
        try {
            tcpMaster.init();
        } catch (ModbusInitException e) {
            log.error("tcpMaster init error: ", e);
        }
        return tcpMaster;
    }

    @Override
    public boolean sendSignal(int slaveId, int switchPort, boolean writeValue) throws Exception {
        // 创建请求
        WriteCoilResponse response;
        try {
            WriteCoilRequest request = new WriteCoilRequest(slaveId, switchPort, writeValue);
            response = (WriteCoilResponse) createTcpMaster().send(request);
        } catch (ModbusTransportException e) {
            throw new RuntimeException(String.format("Master sendSignal to slave %s switchPort %s error: %s",
                    slaveId, switchPort, e.toString()));
        }
        // 发送请求并获取响应对象
        if (response.isException()) {
            log.error("Master sendSignal to slave {} switchPort {} error: {}",
                    slaveId, switchPort, JSON.toJSONString(response));
            throw new Exception(String.format("Master sendSignal to slave %s switchPort %s error: %s",
                    slaveId, switchPort, JSON.toJSONString(response)));
        } else {
            return true;
        }
    }

    public boolean getStatus(int slaveId, int switchPort) throws ErrorResponseException, ModbusTransportException {
        BaseLocator<Boolean> loc = BaseLocator.coilStatus(slaveId, switchPort);
        return createTcpMaster().getValue(loc);
    }
}
