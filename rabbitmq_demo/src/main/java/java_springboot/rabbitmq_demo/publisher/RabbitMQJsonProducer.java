package java_springboot.rabbitmq_demo.publisher;

import java_springboot.rabbitmq_demo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    public String exchange;
    @Value("${rabbitmq.json.routingkey}")
    public String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    //Used to send messages based on constructor based dependency injection
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //Method to send Json message to the queue
    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Json Message sent -> %s",user));
        rabbitTemplate.convertAndSend(exchange,routingKey,user);
    }
}
