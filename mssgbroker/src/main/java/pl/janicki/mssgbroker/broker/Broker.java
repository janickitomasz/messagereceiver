package pl.janicki.mssgbroker.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Broker {

    @Autowired
    TopicHandler topicHandler;

    @Autowired
    JmsProducer jmsProducer;

    Logger log = LoggerFactory.getLogger(Broker.class);

    public Broker(){
    }

    public void send(String message, String topic){
        send(message, topic, null);
    }

    public boolean send(String message, String topic, Long ttl){
        topic = topicHandler.addTopic(topic);
        if(topic!=null){
            try {
                jmsProducer.send(message, topic, ttl);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

}
