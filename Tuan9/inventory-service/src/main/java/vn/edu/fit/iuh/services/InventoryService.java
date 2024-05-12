package vn.edu.fit.iuh.services;

import jakarta.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class InventoryService {
    @JmsListener(destination = "inventory.topic_01")
    public void receiveMessage(Message<String> message) {
        System.out.println("** received message: " + message.getPayload());
    }
}
