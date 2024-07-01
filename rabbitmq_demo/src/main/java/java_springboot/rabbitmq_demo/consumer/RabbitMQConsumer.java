package java_springboot.rabbitmq_demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    //The consumer class consumes message from this particular queue. Hence whenever there is a new message in the queue, the consumer will consume it
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(String message)
    {
        LOGGER.info(String.format("Recieved message -> %s",message));
    }
}
