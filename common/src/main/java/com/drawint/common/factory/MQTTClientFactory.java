package com.drawint.common.factory;

import com.drawint.common.exception.BizException;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTClientFactory {
    private static MqttClient client;

    public static MqttClient getInstance() {
        if (client == null) {
            client = createClient();
        }
        return client;
    }

    private static MqttClient createClient() {
        try {
            MqttClient client = new MqttClient("tcp://192.168.1.102:1883", "testClient");
            if (!client.isConnected()) {
                MqttConnectOptions options = new MqttConnectOptions();
                options.setAutomaticReconnect(true);
                client.connect(options);
            }
            return client;

        } catch (MqttException e) {
            throw new BizException(e);
        }
    }
}
