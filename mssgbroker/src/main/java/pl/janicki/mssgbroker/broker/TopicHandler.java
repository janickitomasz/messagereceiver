package pl.janicki.mssgbroker.broker;

import org.springframework.stereotype.Component;
import pl.janicki.mssgbroker.Utils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicHandler {
    ArrayList<String> topics;

    public TopicHandler(){
        topics = new ArrayList<>();
    }

    public String addTopic(String topic){
        if(topic==null) return null;
        topic= Utils.prepareTopic(topic);
        if(!topics.contains(topic)){
            topics.add(topic);
        }
        return topic;
    }

    public List<String> getTopics(){
        return topics;
    }


}
