package com.drawint.service;

public interface RelayDeviceService {
    boolean portIsOn(int switchPort);

    boolean switchOff(int switchPort);

    boolean switchOn(int switchPort);

    boolean switchStatus(int switchPort);

    boolean switchOnAndOff(int switchPort);
}
