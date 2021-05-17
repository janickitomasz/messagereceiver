package pl.janicki.mssgbroker;

public class Utils {
    public static String prepareTopic(String topic){
        if(topic==null) return null;
        return topic.trim().toLowerCase();
    }
}
