package pl.janicki.mssgbroker.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class JmsProducer {

    Logger log = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message, String topic, Long timeout) throws JMSException {
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setDeliveryPersistent(true);
        Session session = jmsTemplate.getConnectionFactory().createConnection().createSession();
        Destination dest1 = session.createTopic(topic);
        MessageProducer producer = session.createProducer(dest1);
        Message message1 = jmsTemplate.getMessageConverter().toMessage(message, session);
        producer.send(message1, DeliveryMode.PERSISTENT,1, timeout);
        log.info("Sent message:", message);

    }
}
