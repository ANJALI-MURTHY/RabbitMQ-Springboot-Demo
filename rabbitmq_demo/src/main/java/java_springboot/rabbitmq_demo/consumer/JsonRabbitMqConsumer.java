package java_springboot.rabbitmq_demo.consumer;

import java_springboot.rabbitmq_demo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class JsonRabbitMqConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonRabbitMqConsumer.class);

    //The consumer class consumes message from this particular queue. Hence whenever there is a new message in the queue, the consumer will consume it
    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
    public void consumeJsonMessage(User user)
    {
        LOGGER.info(String.format("Recieved Json message -> %s",user.toString()));
    }
}
