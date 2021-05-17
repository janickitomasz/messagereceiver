package pl.janicki.mssgbroker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.janicki.mssgbroker.broker.Broker;
import pl.janicki.mssgbroker.broker.TopicHandler;

import java.util.List;

import static java.lang.Thread.sleep;

@RestController
public class MainController {

    @Autowired
    TopicHandler topicHandler;

    @Autowired
    Broker broker;

    @GetMapping("/generate")
    public void generate(){
        for(int i=0;i<25;i++){
            broker.send("tst"+i,"x1",25000l);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sent "+i);

        }
    }


    @GetMapping("/gettopics")
    public List<String> getTopics(){
        return topicHandler.getTopics();
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String message, @RequestParam String topic, @RequestParam Long ttl){
        if(broker.send(message,topic,ttl)){
            return "Message sent";
        }else{
            return "Message not sent";
        }
    }
}
