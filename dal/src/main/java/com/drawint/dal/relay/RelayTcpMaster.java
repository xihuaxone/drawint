package com.drawint.dal.relay;


import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

public interface RelayTcpMaster {

    boolean sendSignal(int slaveId, int switchPort, boolean writeValue) throws Exception;

    boolean getStatus(int slaveId, int switchPort) throws ErrorResponseException, ModbusTransportException;
}
