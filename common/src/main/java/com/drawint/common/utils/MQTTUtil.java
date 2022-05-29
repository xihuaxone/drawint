package com.drawint.common.utils;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.common.factory.MQTTClientFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

public class MQTTUtil {
    public static void send(String topic, String data) {
        MqttClient client = MQTTClientFactory.getInstance();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes(StandardCharsets.UTF_8));
        try {
            client.publish(topic, message);
        } catch (MqttException e) {
            throw new BizException(BizExceptionType.MQTT_SEND_ERROR, "send data to MQTT error: " + e.toString());
        }
    }

    public static void subscribe() {
        // TODO
    }
}
