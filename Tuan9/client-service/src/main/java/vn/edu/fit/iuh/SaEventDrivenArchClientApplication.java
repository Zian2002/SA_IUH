package vn.edu.fit.iuh;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

@SpringBootApplication
public class SaEventDrivenArchClientApplication implements CommandLineRunner {
    private static final String BROKER_URL = "tcp://127.0.0.1:61616";
    private static final String BROKER_USERNAME = "admin";
    private static final String BROKER_PASSWORD = "admin";

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SaEventDrivenArchClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Configure the connection factory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUserName(BROKER_USERNAME);
        connectionFactory.setPassword(BROKER_PASSWORD);

        // Create the JmsTemplate and configure it to send messages to a topic
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setPubSubDomain(true);

        // Send message to the topic
        jmsTemplate.send("inventory.topic_01", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                String json = "{\"order_id\":\"123456789\",\"items\":[{\"product_id\":\"ABC123\",\"name\":\"Example Product\",\"quantity\":2,\"unit_price\":25.99},{\"product_id\":\"DEF456\",\"name\":\"Another Product\",\"quantity\":1,\"unit_price\":15.49}],\"delivery\":{\"address\":\"123 Main St\",\"city\":\"Anytown\",\"postal_code\":\"12345\",\"country\":\"USA\",\"expected_delivery_date\":\"2024-04-28\"},\"payment\":{\"method\":\"credit_card\",\"card_number\":\"**** **** **** 1234\",\"card_holder_name\":\"John Doe\",\"expiration_date\":\"12/25\",\"cvv\":\"123\"}}\n";
                return session.createTextMessage(json);
            }
        });
    }
}
