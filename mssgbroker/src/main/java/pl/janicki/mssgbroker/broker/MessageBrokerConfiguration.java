package pl.janicki.mssgbroker.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class MessageBrokerConfiguration {


    @Autowired
    private ArtemisProperties artemisProperties;

    @Bean
    public ArtemisConfigurationCustomizer customizer() {
        return new ArtemisConfigurationCustomizer() {
            @Override
            public void customize(org.apache.activemq.artemis.core.config.Configuration configuration) {
                try {
                    configuration.addAcceptorConfiguration("netty", "tcp://"+artemisProperties.getHost() + ":" + artemisProperties.getPort()+"");
                } catch (Exception e) {
                    throw new RuntimeException("Failed to configure transport acceptor", e);
                }
            }
        };
    }
}